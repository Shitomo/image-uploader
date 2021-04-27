package bean;

import com.fasterxml.jackson.annotation.JsonProperty;
// hibernate製のアノテーションを使用
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;

/**
 * 国勢調査データ
 *
 * 都道府県コード
 * 都道府県名
 * 元号
 * 和暦（年）
 * 西暦（年）
 * 注
 * 人口（総数）
 * 人口（男）
 * 人口（女）
 */
public class CensusData {

    private static String mode;

    @NotBlank(message = "都道府県コードは必須です")
    @JsonProperty("都道府県コード")
    private String id;
    @NotBlank(message = "都道府県名は必須です")
    @JsonProperty("都道府県名")
    private String name;
    @JsonProperty("元号")
    private String eraName;
    @JsonProperty("和暦（年）")
    private String japaneseCalendar;
    @JsonProperty("西暦（年）")
    private String westernCalendar;
    @JsonProperty("注")
    private String note;
    @JsonProperty("人口（総数）")
    private String population;
    @JsonProperty("人口（男）")
    private String malePopulation;
    @JsonProperty("人口（女）")
    private String femalePopulation;

    @AssertTrue(message = "詳細モード時は，注は必須です")
    public boolean isNotNotEmptyIfDetailMode() {
        if (mode == null) {
            return true;
        }

        if (mode.equals("DETAIL")) {
            return !note.isEmpty();
        } else {
            return true;
        }
    }

    public static void setMode(String mode) {
        CensusData.mode = mode;
    }

    @Override
    public String toString() {
        return "CensusData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eraName='" + eraName + '\'' +
                ", japaneseCalendar=" + japaneseCalendar +
                ", westernCalendar=" + westernCalendar +
                ", note='" + note + '\'' +
                ", population=" + population +
                ", malePopulation=" + malePopulation +
                ", femalePopulation=" + femalePopulation +
                '}';
    }

    public CensusData(String id, String name, String eraName, String japaneseCalendar, String westernCalendar, String note, String population, String malePopulation, String femalePopulation) {
        this.id = id;
        this.name = name;
        this.eraName = eraName;
        this.japaneseCalendar = japaneseCalendar;
        this.westernCalendar = westernCalendar;
        this.note = note;
        this.population = population;
        this.malePopulation = malePopulation;
        this.femalePopulation = femalePopulation;
    }

    public CensusData() {
    }
}
