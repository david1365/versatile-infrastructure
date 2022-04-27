package ir.caspco.versatile.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Slf4j
public class BufferUtil {

    public static DataBuffer join(List<? extends DataBuffer> dataBuffers) {

        StringBuffer stringBuffer = new StringBuffer();
        dataBuffers.forEach(dataBuffer -> {

            byte[] content = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(content);
            DataBufferUtils.release(dataBuffer);

            try {
                stringBuffer.append(new String(content, Charset.defaultCharset()));
            } catch (Exception e) {
                log.error("Convert To String Error: ", e);

                throw e;
            }

        });

        String result = stringBuffer.toString();

        byte[] content = result.getBytes(Charset.defaultCharset());
        return new DefaultDataBufferFactory().wrap(content);

    }

}
