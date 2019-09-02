package net.depo.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by andmat on 2019-08-18.
 */
public class Transport implements Depo {

    public static int max;
    private int transport_id;
    private String number;
    //    private Timestamp created_at;
//    private Timestamp modified_at;
    private String created_at;
    private String modified_at;
    private int kind_id;
    private int park_id;
    private int capacity_id;
    private String kind;
    private int park_number;
    private String park_type;
    private int type_id;
    private String type;
    private BigDecimal width;
    private BigDecimal length;
    private int capacity;
    private double tonnage;
    private String transport_type;
    private String kind_or_type;

    public Transport() {
    }

    public Transport(int transport_id, String number, String created_at, String modified_at,
                     String kind, int park_number, String park_type, BigDecimal width, BigDecimal length,
                     int capacity, double tonnage, String transport_type) {
        this.transport_id = transport_id;
        this.number = number;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.kind = kind;
        this.park_number = park_number;
        this.park_type = park_type;
        this.width = width;
        this.length = length;
        this.capacity = capacity;
        this.tonnage = tonnage;
        this.transport_type = transport_type;
    }

    public Transport(String kind) {
        this.kind = kind;
    }

    public Transport(int type_id, String type) {
        this.type_id = type_id;
        this.type = type;
    }

    public Transport(int park_id, String park_type, int park_number) {
        this.park_type = park_type;
        this.park_number = park_number;
        this.park_id = park_id;
    }

    public int getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(int transport_id) {
        this.transport_id = transport_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public String getKind_or_type() {
        return kind_or_type;
    }

    public void setKind_or_type(String kind_or_type) {
        this.kind_or_type = kind_or_type;
    }

    public int getKind_id() {
        return kind_id;
    }

    public void setKind_id(int kind_id) {
        this.kind_id = kind_id;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public int getCapacity_id() {
        return capacity_id;
    }

    public void setCapacity_id(int capacity_id) {
        this.capacity_id = capacity_id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getPark_number() {
        return park_number;
    }

    public void setPark_number(int park_number) {
        this.park_number = park_number;
    }

    public String getPark_type() {
        return park_type;
    }

    public void setPark_type(String park_type) {
        this.park_type = park_type;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public double getTonnage() {
        return tonnage;
    }

    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
    }

    public String getTransport_type() {
        return transport_type;
    }

    public void setTransport_type(String transport_type) {
        this.transport_type = transport_type;
    }
}
