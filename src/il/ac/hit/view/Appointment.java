package il.ac.hit.view;

import org.jetbrains.annotations.NotNull;

/*
NO TEST NEEDED BECAUSE THE METHODS ARE ALL GETTERS AND SETTERS WITHOUT CALCULATIONS
 */
public class Appointment {

/**
Build an appointment that includes date, time, client name, hairstyle type and customer phone.
This class has getters and setters methods.
 */
    private String date;
    private String hour;
    private String customerName;
    private String typeHairCut;
    private String customerTel;

    /**
     * Constructor Details appointment
     * @param date the date of the appointment, Can't be null
     * @param hour the hour of the appointment, Can't be null
     * @param customerName the customer's name, Can't be null
     * @param typeHairCut the haircut type, Can't be null
     * @param customerTel the customer's telephone number, Can't be null
     */
    public Appointment(@NotNull String date, @NotNull String hour,@NotNull String customerName,@NotNull String typeHairCut,@NotNull String customerTel)
    {
        setDate(date);
        setHour(hour);
        setCustomerName(customerName);
        setTypeHairCut(typeHairCut);
        setCustomerTel(customerTel);
    }

    /**
     * Get the date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set date
     * @param date appointment date, Can't be null
     */
    public void setDate(@NotNull String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    /**
     * Set hour
     * @param hour appointment time, Can't be null
     */
    public void setHour(@NotNull String hour) {
        this.hour = hour;
    }

    /**
     * Get the name of customer
     * @return name name of customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Set custom name
     * @param customerName customer's name, Can't be null
     */
    public void setCustomerName(@NotNull String customerName) {
        this.customerName = customerName;
    }

    public String getTypeHairCut() {
        return typeHairCut;
    }

    /**
     * Set the hair cut type
     * @param typeHairCut haircut type, Can't be null
     */
    public void setTypeHairCut(@NotNull String typeHairCut) {
        this.typeHairCut = typeHairCut;
    }

    /**
     * Get the telephone customer
     * @return customerTel telephone of customer
     */
    public String getCustomerTel() {
        return customerTel;
    }

    /**
     * Set customer telephone
     * @param customerTel customer's telephone number, Can't be null
     */
    public void setCustomerTel(@NotNull String customerTel) {
        this.customerTel = customerTel;
    }

}
