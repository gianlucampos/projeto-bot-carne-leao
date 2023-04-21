package br.com.main.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class Dividend {

    private String dtActive;
    private String codeActive;
    private String nameActive;
    private String value;
    private String taxes;

    public Dividend(String dtActive, String codeActive, String nameActive, String value, String taxes) {
        this.dtActive = dtActive;
        this.codeActive = codeActive;
        this.nameActive = ActiveEnum.valueOf(codeActive).getDescription();
        this.value = value;
        this.taxes = taxes;
    }

    public void setCodeActive(String codeActive) {
        this.codeActive = codeActive;
        this.nameActive = ActiveEnum.valueOf(codeActive).getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dividend dividend = (Dividend) o;
        return Objects.equals(dtActive, dividend.dtActive) && Objects.equals(codeActive, dividend.codeActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dtActive, codeActive);
    }

    @Override
    public String toString() {
        return "Dividend{" +
                "dtActive='" + dtActive + '\'' +
                ", nameActive='" + codeActive + '\'' +
                ", value='" + value + '\'' +
                ", taxes='" + taxes + '\'' +
                '}' + "\n";
    }
}
