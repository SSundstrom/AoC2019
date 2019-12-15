package Day14;

public class Item {
    private final String name;
    private long amountAvailable;
    private long created;

    public Item(String name) {
        this.name = name;
        this.amountAvailable = 0;
        this.created = 0;
    }

    public String getName() {
        return name;
    }

    public long getAmountAvailable() {
        return amountAvailable;
    }

    public void useItem(int amount) {
        amountAvailable -= amount;
    }

    public void created(long amount) {
        amountAvailable += amount;
        created += amount;
    }

    public long getCreated() {
        return created;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
