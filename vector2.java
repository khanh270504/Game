public class vector2{
    public int x, y;

    public vector2(int _x, int _y){
        x = _x;
        y = _y;
    }

    public vector2 add(vector2 v){
        return new vector2(x + v.x, y + v.y);
    }
    
}