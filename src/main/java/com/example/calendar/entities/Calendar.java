package com.example.calendar.entities;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;


@Entity(name = "Calendar")
@Table(name="calendar")
// @TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class Calendar {

    @Id
    private String id;

    private String message;

    private Date date;

    private String status;


    // @Type( type = "jsonb" )
    @Column(columnDefinition = "jsonb")
    @Convert(converter = MetaDataConverter.class)
    @SuppressWarnings("JpaAttributeTypeInspection")
    private Map<String, Object> metaData;

//    @Type(type = "jsonb")
//    @Column(columnDefinition = "jsonb")
//    private List<String> metaData = new ArrayList();
}
