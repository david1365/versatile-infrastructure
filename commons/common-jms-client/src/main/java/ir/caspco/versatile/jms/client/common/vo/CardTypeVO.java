package ir.caspco.versatile.jms.client.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class CardTypeVO {

    private Long id;
    private Long cardTypeCode;
    private String title;
    private Date activateDate;
    private Date deActivateDate;
    private Date lastReview;
    private String physicType;
    private Integer authenticationType;
    private String pinBlockType;
    private String pinEncryptionType;
    private Integer pinLength;
    private Integer panLength;
    private String cardLanguage;
    private Boolean addCVV;
    private Boolean addPVV;
    private Boolean addIssueInformation;
    private Boolean addSerial;
    private Long currentSequence;
    private Integer isActive;
    private byte[] frontImage;
    private byte[] backImage;
    private byte[] varnishImage;
    private Boolean hasVarnishImage;
    private Long version;
    private List<CardTypeItemPrintFormatVO> cardTypeItemPrintFormatDto;
}
