package sample;


import javafx.scene.paint.Color;

/** On créer une classe pour pouvoir interagir avec les différentes formes qui seront crée **/

/** But de cette classes dans le future, pour le clonage / la supression / et le déplacement. Quand on click dans
 *  le canva, on vérifie si le click est "sur" une forme, et dans ce cas on effectue les changements adéquat dans
 *  notre ArrayList de Forms (cf controlleur)
 **/
public class Forms {

    private String form;
    private double x;
    private double y;
    private Color color;

    public Forms(String form, double x, double y, Color c){
        this.form = form;
        this.x=x;
        this.y=y;
        this.color = c;
    }

    public String getform(){
        return this.form;
    }

    public double getX (){
        return this.x;
    }

    public double getY (){
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }

    /** Va nous permettre de récuperer les objet lors d'un click dans le canva **/
    public boolean IsInside(double x, double y){
        if(this.form == "line" && (this.x <= x && x <= this.x+30 && this.y == y)){
            return true;
        }
        if(this.form == "ellipse" && ((this.x <= x && x <= this.x+20) && (this.y == y && y <= this.y+30))){
            return true;
        }
        if(this.form == "rect" && ((this.x <= x && x <= this.x+30) && (this.y == y && y <= this.y+40))){
            return true;
        }
        return false;
    }
}
