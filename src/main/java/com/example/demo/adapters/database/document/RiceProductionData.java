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

    public String getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getYear() {
        return year;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        return value;
    }

    public String getFlag() {
        return flag;
    }

    public String getFlagDescription() {
        return flagDescription;
    }
}
