package GameMap;

public class MyMap {
    private Object[][] map;

    public MyMap(int width, int height) {
        this.map = new Object[width][height];
    }

    public Object[][] getMap() {
        return map;
    }

    public void setMap(Object[][] map) {
        this.map = map;
    }

    public Object getObject(int x, int y) {
        return null;
    }

    public void setObject(int x, int y, Object object) {
    }
}
