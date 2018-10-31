package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/9/27.
 */

public class CarsTypeBean {

    /**
     * space : null
     * car_qb_gl : null
     * car_state : null
     * create_time : null
     * car_type_qb_price : null
     * car_type_gl_price : null
     * car_type_id : 2
     * creater : null
     * changer : null
     * car_load : null
     * change_time : null
     * car_type_name : 小型平板
     */

    private Object space;
    private Object car_qb_gl;
    private Object car_state;
    private Object create_time;
    private Object car_type_qb_price;
    private Object car_type_gl_price;
    private String car_type_id;
    private Object creater;
    private Object changer;
    private Object car_load;
    private Object change_time;
    private String car_type_name;

    public Object getSpace() {
        return space;
    }

    public void setSpace(Object space) {
        this.space = space;
    }

    public Object getCar_qb_gl() {
        return car_qb_gl;
    }

    public void setCar_qb_gl(Object car_qb_gl) {
        this.car_qb_gl = car_qb_gl;
    }

    public Object getCar_state() {
        return car_state;
    }

    public void setCar_state(Object car_state) {
        this.car_state = car_state;
    }

    public Object getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Object create_time) {
        this.create_time = create_time;
    }

    public Object getCar_type_qb_price() {
        return car_type_qb_price;
    }

    public void setCar_type_qb_price(Object car_type_qb_price) {
        this.car_type_qb_price = car_type_qb_price;
    }

    public Object getCar_type_gl_price() {
        return car_type_gl_price;
    }

    public void setCar_type_gl_price(Object car_type_gl_price) {
        this.car_type_gl_price = car_type_gl_price;
    }

    public String getCar_type_id() {
        return car_type_id;
    }

    public void setCar_type_id(String car_type_id) {
        this.car_type_id = car_type_id;
    }

    public Object getCreater() {
        return creater;
    }

    public void setCreater(Object creater) {
        this.creater = creater;
    }

    public Object getChanger() {
        return changer;
    }

    public void setChanger(Object changer) {
        this.changer = changer;
    }

    public Object getCar_load() {
        return car_load;
    }

    public void setCar_load(Object car_load) {
        this.car_load = car_load;
    }

    public Object getChange_time() {
        return change_time;
    }

    public void setChange_time(Object change_time) {
        this.change_time = change_time;
    }

    public String getCar_type_name() {
        return car_type_name;
    }

    public void setCar_type_name(String car_type_name) {
        this.car_type_name = car_type_name;
    }
    @Override
    public String toString() {
        //重写该方法，作为选择器显示的名称
        return car_type_name;
    }
}
