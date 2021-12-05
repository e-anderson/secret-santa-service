package not.nerds.secretsantaservice.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="match")
public class Match {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="exchange_id")
    private Exchange exchange;

    @ManyToOne
    @JoinColumn(name="gifter_id")
    private User gifter;

    @ManyToOne
    @JoinColumn(name="recipient_id")
    private User recipient;

    @Column(name="is_fulfilled")
    private boolean isFulfilled;

    @Column(name="fulfillment_date")
    private Date fulfillmentDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public User getGifter() {
        return gifter;
    }

    public void setGifter(User gifter) {
        this.gifter = gifter;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        isFulfilled = fulfilled;
    }

    public Date getFulfillmentDate() {
        return fulfillmentDate;
    }

    public void setFulfillmentDate(Date fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }
}
