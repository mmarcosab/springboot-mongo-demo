package com.example.demo.adapters.database.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("rice_production")
public class RiceProductionData {

    @Id
    private String id;
    @Field("Area")
    private String area;
    @Field("Year")
    private String year;
    @Field("Unit")
    private String unit;
    @Field("Value")
    private String value;
    @Field("Flag")
    private String flag;
    @Field("Flag Description")
    private String flagDescription;

    public RiceProductionData() {

    }
    public RiceProductionData(final String area,
                              final String year,
                              final String unit,
                              final String value,
                              final String flag,
                              final String flagDescription
    ) {
        this.area = area;
        this.year = year;
        this.unit = unit;
        this.value = value;
        this.flag = flag;
        this.flagDescription = flagDescription;
    }

    public static RiceProductionData of(
            final String area,
            final String year,
            final String value,
            final String flag
    ) {
        return new RiceProductionData(area, year, null, value, flag, null);
    }

    public static RiceProductionData update(final String id, final RiceProductionData riceProductionData) {
        final RiceProductionData riceProductionDataUpdated =  new RiceProductionData();
        riceProductionDataUpdated.setId(id);
        riceProductionDataUpdated.setArea(riceProductionData.getArea() == null? null : riceProductionData.getArea());
        riceProductionDataUpdated.setFlag(riceProductionData.getFlag() == null? null : riceProductionData.getFlag());
        riceProductionDataUpdated.setYear(riceProductionData.getYear() == null? null : riceProductionData.getYear());
        riceProductionDataUpdated.setUnit(riceProductionData.getUnit() == null? null : riceProductionData.getUnit());
        riceProductionDataUpdated.setValue(riceProductionData.getValue() == null? null : riceProductionData.getValue());
        riceProductionDataUpdated.setFlagDescription(riceProductionData.getFlagDescription() == null? null : riceProductionData.getFlagDescription());
        return riceProductionDataUpdated;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagDescription() {
        return flagDescription;
    }

    public void setFlagDescription(String flagDescription) {
        this.flagDescription = flagDescription;
    }
}
