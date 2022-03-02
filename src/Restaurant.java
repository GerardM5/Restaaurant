import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private static final int MAX_CAPACITY = 24;
    private static final int MAX_TABLES = 4;


    private String name;
    private List<Table> tables = new ArrayList<>();

    public Restaurant(String name) throws Exception {
        checkName(name);
        this.name = name;
    }

    private void checkName(String name) throws Exception {
        if(name.equals("")) throw new Exception("Nombre vacio");
    }

    public String getName() {
        return name;
    }

    public int getCurrentCapacity() {
        int currentCapacity = 0;
        for (Table currentTable: tables) {

            currentCapacity += currentTable.getPeople();

        }
        return currentCapacity;

        //return tables.stream()
        //        .mapToInt(tables::get)
        //        .sum();

    }


    private void checkIfCanEntry(int people) throws Exception {
        if (getCurrentCapacity() + people > MAX_CAPACITY) throw new Exception("No caben");
    }

    public void addTable(int comensals) throws Exception {
        checkIfCanEntry(comensals);
        int necessaryTables = howManyTablesNeed(comensals);
        checkEmptyTables(necessaryTables);
        sitComensalsAtTables(necessaryTables,comensals);

    }

    private void sitComensalsAtTables(int necessaryTables, int comensals) throws Exception {
        for (int i = 0; i < necessaryTables; i++) {
            tables.add(new Table(calculatePeopleForTable(comensals)));
            comensals-=6;
        }

    }

    private int calculatePeopleForTable(int comensals) {//estaria en table
        if (comensals>6){
            return 6;
        }else return comensals;
    }


    private void checkEmptyTables(int necessaryTables) throws Exception {
        if(tables.size()+necessaryTables>MAX_TABLES) throw new Exception();
    }

    private int howManyTablesNeed(int comensals) {
        return (int) Math.ceil((double)comensals/Table.getMaxTableCapacity());
    }

    public void deleteTable(int table) throws Exception {
        checkIfCanLeave(table);
        tables.remove(table-1);
    }

    private void checkIfCanLeave(int table) throws Exception {
        if (tables.size()<table) throw new Exception("Mesa vacia");
    }

    public String getTables() {
        StringBuilder printedTables= new StringBuilder();
        for (int i = 0; i < tables.size(); i++) {

            printedTables.append("Mesa ").append(i + 1).append(" = ").append(tables.get(i).getPeople()).append(" comensales\n");

        }
        return printedTables.toString();
    }

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }
}
