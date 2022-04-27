package ir.caspco.versatile.gateway.management.configuration;

import ir.caspco.versatile.common.util.reflect.ReflectUtil;
import ir.caspco.versatile.gateway.common.context.management.configuration.RouterManagement;
import ir.caspco.versatile.gateway.smart.filters.annotations.EnableSmartFilters;
import ir.caspco.versatile.gateway.smart.filters.annotations.Filter;
import ir.caspco.versatile.gateway.smart.filters.SmartGatewayFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Slf4j
@Configuration
public class GateWayManagement {

    private static ApplicationContext applicationContext;

    @Value("${gateway.routes}")
    private Set<String> routeConfigs;

    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {

        GateWayManagement.applicationContext = applicationContext;

    }

    public static ApplicationContext applicationContext() {
        return applicationContext;
    }


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {

        RouteLocatorBuilder.Builder routes = builder.routes();

        this.addRoutes(routes);

        return routes.build();

    }

    private void addRoutes(RouteLocatorBuilder.Builder routes) {

        routeConfigs.forEach(routeConfig -> {

            Object configuration = applicationContext.getBean(routeConfig);

            Collection<GatewayFilter> smartFilters = gatewayFilters(configuration.getClass());

            Method[] routeLocatorMethods = MethodUtils.getMethodsWithAnnotation(configuration.getClass(), RouterManagement.class);
            if (routeLocatorMethods.length > 0) {

                Method routeLocatorMethod = routeLocatorMethods[0];

                ReflectUtil.smartInvoke(routeLocatorMethod, configuration, routes, smartFilters);

            }

        });

    }

    //TODO from davood akbari: Do not forget to find the right solution to get beans
    protected Collection<GatewayFilter> gatewayFilters(Class<?> configurationClass) {

        if (!isEnableSmartFilters(configurationClass)) {
            return Collections.emptyList();
        }

        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(Filter.class);


        Stream<Object> filterStream = beanMap.values().stream();
        if (!basePackages(configurationClass).isEmpty())
            filterStream = filterStream.filter(filter -> contains(filter.getClass().getPackage().getName(), configurationClass));

        return filterStream
                .filter(filter -> filter instanceof SmartGatewayFilter)
                .map(filter -> apply(filter, configurationClass))
                .collect(Collectors.toList());

    }

    private boolean isEnableSmartFilters(Class<?> configurationClass) {
        return ReflectUtil.realClassObject(configurationClass).isAnnotationPresent(EnableSmartFilters.class);
    }

    private EnableSmartFilters enableSmartFilters(Class<?> configurationClass) {
        return ReflectUtil.realClassObject(configurationClass).getAnnotation(EnableSmartFilters.class);
    }

    private List<String> basePackages(Class<?> configurationClass) {
        return isEnableSmartFilters(configurationClass) ? Arrays.asList(enableSmartFilters(configurationClass).basePackages()) : Collections.emptyList();
    }

    private String filterPrefix(Class<?> configurationClass) {
        return isEnableSmartFilters(configurationClass) ? enableSmartFilters(configurationClass).prefix() : "";
    }

    private boolean contains(String className, Class<?> configurationClass) {
        return basePackages(configurationClass).stream().anyMatch(className::startsWith);
    }

    private GatewayFilter apply(Object filter, Class<?> configurationClass) {
        SmartGatewayFilter smartGatewayFilter = (SmartGatewayFilter) filter;
        smartGatewayFilter.init(filterPrefix(configurationClass));

        log.info(ReflectUtil.getRealName(smartGatewayFilter.getClass()) + " is applying.");

        return smartGatewayFilter.apply();
    }

}
