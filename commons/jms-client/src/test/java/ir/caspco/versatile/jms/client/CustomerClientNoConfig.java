package ir.caspco.versatile.jms.client;

import ir.caspco.versatile.jms.client.message.Customer;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class CustomerClientNoConfig extends JmsClient<Customer, Customer> {
}
