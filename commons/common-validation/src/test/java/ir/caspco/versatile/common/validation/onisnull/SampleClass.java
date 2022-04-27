package ir.caspco.versatile.common.validation.onisnull;

import ir.caspco.versatile.common.validation.annotations.OneIsNotNull;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class SampleClass {

    public void type(@Valid SampleModel sampleModel) {

    }

    public void param(@OneIsNotNull SampleModelFieldParam sampleModelFieldParam) {

    }

}
