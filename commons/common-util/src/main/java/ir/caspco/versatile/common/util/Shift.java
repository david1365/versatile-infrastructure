package ir.caspco.versatile.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.caspco.versatile.common.util.exceptions.ClassObjectIsNotSetException;
import lombok.SneakyThrows;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.function.Consumer;
import java.util.function.Function;

import static ir.caspco.versatile.common.util.FileUtil.exists;
import static ir.caspco.versatile.common.util.FileUtil.readFileFromResource;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Separate Method in their own class.
public class Shift<T> {

    private static final String suffix = "json";

    private static ObjectMapper mapper;
    private static ObjectWriter writer;

    private static final Charset defaultCharset = StandardCharsets.UTF_8;
    public static final String defaultDateFormat = "yyyy-MM-dd HH:mm:ss.S";

    private final Charset charset;
    private byte[] dataBytes;
    private final String jsonString;
    private final Class<T> tClass;

    private ObjectMapper instanceMapper;
    private ObjectWriter instanceWriter;


    static {
        setDateFormat(defaultDateFormat);
    }

    private static ObjectWriter configDateFormat(ObjectMapper mapper, String StringDateFormat) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(StringDateFormat);

        mapper.setDateFormat(dateFormat);

        return mapper.writerWithDefaultPrettyPrinter();

    }

    public static void setDateFormat(String dateFormat) {

        mapper = new ObjectMapper();

        config(mapper);

        writer = configDateFormat(mapper, dateFormat);
    }

    public static void setDefaultDateFormat() {
        setDateFormat(defaultDateFormat);
    }

    @SneakyThrows
    public Shift<T> dateFormat(String dateFormat) {

        Object object = null;
        if (tClass != null) {
            object = mapper().readValue(buffer(), tClass);
        }

        instanceMapper = new ObjectMapper();
        instanceWriter = configDateFormat(instanceMapper, dateFormat);

        config(instanceMapper);

        if (tClass != null) {
            dataBytes = instanceWriter.writeValueAsBytes(object);
        }

        return this;

    }

    private static void config(ObjectMapper mapper) {

        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    private ObjectMapper mapper() {
        return instanceMapper != null ? instanceMapper : mapper;
    }

    private ObjectWriter writer() {
        return instanceWriter != null ? instanceWriter : writer;
    }

    //TODO from davood akbari: Find the best solution for Constructors.
    private Shift(String jsonString, Charset charset) {

        this.dataBytes = null;
        this.tClass = null;

        this.charset = charset;
        this.jsonString = jsonString;

    }

    private Shift(DataBuffer dataBytes, Class<T> tClass) {

        this.dataBytes = toBytes(dataBytes);
        this.tClass = tClass;
        this.charset = defaultCharset;

        this.jsonString = null;

    }

    private <R> Shift(R target, Class<T> tClass) {

        this.dataBytes = toBytes(target);
        this.tClass = tClass;
        this.charset = defaultCharset;

        this.jsonString = null;

    }

    private Shift(byte[] dataBytes, Class<T> tClass) {

        this.dataBytes = dataBytes;
        this.tClass = tClass;
        this.charset = defaultCharset;

        this.jsonString = null;

    }

    private byte[] buffer() {
        if (dataBytes == null) {
            return jsonString.getBytes(charset);
        }

        return dataBytes;
    }

    public byte[] toBytes() {
        return buffer();
    }

    @SneakyThrows
    public <R> byte[] toBytes(R target) {

        return writer().writeValueAsBytes(target);

    }

    public byte[] toBytes(DataBuffer dataBuffer) {
        DataBuffer clonedDataBuffer = cloneDataBuffer(dataBuffer);

        byte[] bytes = new byte[clonedDataBuffer.readableByteCount()];

        clonedDataBuffer.read(bytes);
        DataBufferUtils.release(clonedDataBuffer);

        return bytes;
    }

    public static DataBuffer cloneDataBuffer(DataBuffer dataBuffer) {

        if (dataBuffer == null) {
            return null;
        }

        DataBufferUtils.retain(dataBuffer);
        return dataBuffer.slice(0, dataBuffer.readableByteCount());

    }


    public String toJson(Charset charset) {
        byte[] bytes = toBytes();

        return new String(bytes, charset);
    }

    public String toJson() {
        return toJson(charset);
    }

    @SneakyThrows
    public T toObject() {
        if (tClass == null) {
            throw new ClassObjectIsNotSetException();
        }

        return toObject(tClass);
    }

    @SneakyThrows
    public <E> E toObject(Class<E> eClass) {

        return mapper().readValue(toBytes(), eClass);

    }

    @SneakyThrows
    public DataBuffer toDataBuffer() {
        return new DefaultDataBufferFactory().wrap(buffer());
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <R> Shift<R> just(R target) {
        assert target != null;

        return (Shift<R>) new Shift<>(target, target.getClass());
    }

    @SneakyThrows
    public static Shift<?> just(String target, Charset charset) {
        assert target != null;
        assert charset != null;

        return new Shift<>(target, charset);

    }

    public static Shift<?> just(String target) {

        return just(target, defaultCharset);

    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <R> Shift<R> just(DataBuffer dataBuffer) {
        assert dataBuffer != null;

        return (Shift<R>) new Shift<>(dataBuffer, dataBuffer.getClass());
    }

    @SuppressWarnings("unchecked")
    public <R> Shift<R> map(Function<T, R> function) {
        R result = function.apply(toObject());

        Shift<R> shift = (Shift<R>) new Shift<>(toBytes(result), result.getClass());

        setInstanceMapper(shift);

        return shift;
    }

    @SuppressWarnings("unchecked")
    public <R> Shift<R> subscribe(Consumer<T> consumer) {
        consumer.accept(toObject());

        return (Shift<R>) this;
    }

    public <R> Shift<R> toShift(Class<R> rClass) {

        Shift<R> shift = new Shift<>(this.buffer(), rClass);

        setInstanceMapper(shift);

        return shift;

    }

    private <R> void setInstanceMapper(Shift<R> shift) {

        shift.instanceMapper = this.instanceMapper;
        shift.instanceWriter = this.instanceWriter;

    }

    @SneakyThrows
    public static Shift<?> fromFile(String path) {

        return fromFile(path, defaultCharset);

    }

    @SneakyThrows
    public static Shift<?> fromFile(String path, String activatedProfile) {

        return fromFile(path, defaultCharset, activatedProfile);

    }

    @SneakyThrows
    public static Shift<?> fromFile(String path, Charset charset) {

        return fromFile(path, charset, "");

    }

    @SneakyThrows
    public static Shift<?> fromFile(String path, Charset charset, String activatedProfile) {

        final String pathWithoutSuffix = path.replace(".", "/");
        final String defaultPath = String.format("%s.%s", pathWithoutSuffix, suffix);
        final String activatedProfilePath = String.format("%s~%s.%s", pathWithoutSuffix, activatedProfile, suffix);

        path = exists(activatedProfilePath) ? activatedProfilePath : defaultPath;

        String jsonContent = readFileFromResource(path);

        return just(jsonContent, charset);

    }

}
