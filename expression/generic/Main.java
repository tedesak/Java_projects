package expression.generic;

public class Main {
    public static void main(String[] args) throws Exception {
        GenericTabulator tabulator = new GenericTabulator();
        String mode = args[0].substring(1);
        String expression = args[1];
        int x1 = -2;
        int x2 = 2;
        int y1 = -2;
        int y2 = 2;
        int z1 = -2;
        int z2 = 2;
        Object[][][] obj = tabulator.tabulate(mode, expression, x1, x2, y1, y2, z1, z2);
        for (int i = 0; i != obj.length; i++) {
            for (int j = 0; j != obj[i].length; j++) {
                for (int k = 0; k != obj[i][j].length; k++) {
                    System.out.println("for x = " + (x1 + i) + ", y = " + (y1 + j) + ", z = " + (z1 + k));
                    System.out.println(obj[i][j][k]);
                }
            }
        }
    }
}
