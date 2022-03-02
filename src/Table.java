public class Table {


    private static final int MAX_TABLE_CAPACITY = 6;

    private int people;

    public Table(int people) throws Exception {
        checkPeople(people);
        this.people = people;
    }

    public int getPeople() {

        return people;
    }

    private void checkPeople(int people) throws Exception {
        if (people > MAX_TABLE_CAPACITY) throw new Exception("Solo caben 6 en la tabla");
    }

    public static int getMaxTableCapacity() {
        return MAX_TABLE_CAPACITY;
    }
}
