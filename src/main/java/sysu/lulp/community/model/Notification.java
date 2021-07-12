package sysu.lulp.community.model;

public class Notification {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.id
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.notifier
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    private Integer notifier;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.notifier_name
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    private String notifierName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.outer_id
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    private Integer outerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.outer_title
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    private String outerTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.type
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.gmt_create
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.status
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.receiver
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    private Integer receiver;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.id
     *
     * @return the value of notification.id
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.id
     *
     * @param id the value for notification.id
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.notifier
     *
     * @return the value of notification.notifier
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public Integer getNotifier() {
        return notifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.notifier
     *
     * @param notifier the value for notification.notifier
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public void setNotifier(Integer notifier) {
        this.notifier = notifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.notifier_name
     *
     * @return the value of notification.notifier_name
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public String getNotifierName() {
        return notifierName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.notifier_name
     *
     * @param notifierName the value for notification.notifier_name
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public void setNotifierName(String notifierName) {
        this.notifierName = notifierName == null ? null : notifierName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.outer_id
     *
     * @return the value of notification.outer_id
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public Integer getOuterId() {
        return outerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.outer_id
     *
     * @param outerId the value for notification.outer_id
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public void setOuterId(Integer outerId) {
        this.outerId = outerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.outer_title
     *
     * @return the value of notification.outer_title
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public String getOuterTitle() {
        return outerTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.outer_title
     *
     * @param outerTitle the value for notification.outer_title
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public void setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle == null ? null : outerTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.type
     *
     * @return the value of notification.type
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.type
     *
     * @param type the value for notification.type
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.gmt_create
     *
     * @return the value of notification.gmt_create
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.gmt_create
     *
     * @param gmtCreate the value for notification.gmt_create
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.status
     *
     * @return the value of notification.status
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.status
     *
     * @param status the value for notification.status
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.receiver
     *
     * @return the value of notification.receiver
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public Integer getReceiver() {
        return receiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.receiver
     *
     * @param receiver the value for notification.receiver
     *
     * @mbg.generated Thu Jul 08 21:17:16 CST 2021
     */
    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }
}