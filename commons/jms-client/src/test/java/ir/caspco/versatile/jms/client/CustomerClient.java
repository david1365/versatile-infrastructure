package ir.caspco.versatile.jms.client;


import ir.caspco.versatile.context.jms.client.annotations.JmsHeader;
import ir.caspco.versatile.jms.client.message.Customer;
import org.springframework.stereotype.Component;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
@JmsHeader(serviceId = "channelManagement.GetCustomerRelationsMsg")
@Component
public class CustomerClient extends JmsClient<Customer, Customer> {
}
