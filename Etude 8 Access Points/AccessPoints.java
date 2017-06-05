import java.util.*;
import java.awt.geom.Point2D;
import java.awt.Point;

public class AccessPoints{
  
  private static ArrayList<String> coords = new ArrayList<String>();
  private static ArrayList<Point2D> points = new ArrayList<Point2D>();
  private static ArrayList<Integer> radii2 = new ArrayList<Integer>();
  private static ArrayList<Integer> radii3 = new ArrayList<Integer>();
  private static int maxCount2;
  private static int maxCount3;
  private static int radi2;
  private static int radi3;
  private static float n2;
  private static float n3;
  
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    while(scan.hasNextLine()){
      String line = scan.nextLine();
      if(line.equals("")){
        setUpPoints();
      }
      Scanner sc = new Scanner(line);
      coords.add(sc.nextFloat() + " " + sc.nextFloat());
    }
    setUpPoints();
    nC2();
    nC3();
    float radius = 0.00f;
    if(n2 < n3 && maxCount2 == maxCount3){
      radius = n2/100.00f;
    }else if(n2 > n3 && maxCount2 == maxCount3){
      radius = n3/100.00f;
    }else if(maxCount2 > maxCount3){
      radius = n2/100.00f;
    }else if(maxCount2 < maxCount3){
      radius = n3/100.00f;
    }
    System.out.println(n2/100.00f + " or " + n3/100.00f);
    points.clear();
    coords.clear();
  }
  
  public static void setUpPoints(){
    for(String coord : coords){
      Float x = Float.parseFloat(coord.split(" ")[0])*100;
      Float y = Float.parseFloat(coord.split(" ")[1])*100;
      Integer xInt = Math.round(x);
      Integer yInt = Math.round(y);
      points.add(new Point(xInt, yInt));
    }
  }
  
  public static void nC2(){
    int maxCount = 0;
    for(int i = 0; i < points.size(); i++){
      Point2D initial = points.get(i);
      for(int j = 0; j < points.size(); j++){
        if(i != j){
          Point2D second = points.get(j);
          int radius = (int)second.distance(initial) /2;
          int midpointX = (int)(initial.getX()+second.getX())/2;
          int midpointY = (int)(initial.getY()+second.getY())/2;
          Point2D mid = new Point(midpointX, midpointY);
          int count = 0;
          if(radi2 == 0 || radius < radi2){
          for(int num = 0; num < points.size(); num++){
            Point2D point = points.get(num);
            if((int)point.distance(mid) <= radius){
              count++;
            }
            if(count > 11){
              break;
            }
          }
          if(count <= 11){
            if(maxCount < count){
              maxCount = count;
              radii2.clear();
            }
            if(count == maxCount){
              radii2.add(radius);
              radi2 = radius;
            }
          }
          }
        }
      }
    }
    n2 = radi2;
    maxCount2 = maxCount;
    radii2.clear();
  }
  
  
  public static void nC3(){
    int maxCount = 0;
    for(int i = 0; i < points.size(); i++){
      Point2D p1 = points.get(i);
      for(int j = 0; j < points.size(); j++){
        if(i != j){
          Point2D p2 = points.get(j);
          for(int k = 0; k < points.size(); k++){
            if(k != i && k != j){
              Point2D p3 = points.get(k);
              
              Point2D center = midPointCircle(p1, p2, p3);
              int radius = (int)p1.distance(center);
              if(radi3 == 0 || radius < radi3){
                int count = 0;
                for(int num = 0; num < points.size(); num++){
                  Point2D point = points.get(num);
                  if((int)point.distance(center) <= radius){
                    count++;
                  }
                  if(count > 11){
                    break;
                  }
                }
                
                if(count <= 11){
                  if(maxCount < count){
                    maxCount = count;
                    radii3.clear();
                  }
                  if(count == maxCount){
                    radii3.add(radius);
                    radi3 = radius;
                  }
                }
              }
            }
          }
        }
      }
    }
    n3 = radi3;
    maxCount3 = maxCount;
    radii3.clear();
  }
  
  public static Point2D midPointCircle(Point2D p1, Point2D p2, Point2D p3) {
    
    double yDelta_a = p2.getY() - p1.getY();
    double xDelta_a = p2.getX() - p1.getX();
    double yDelta_b = p3.getY() - p2.getY();
    double xDelta_b = p3.getX() - p2.getX();
    Point2D center = new Point(0,0);
    
    double aSlope = yDelta_a/xDelta_a;
    double bSlope = yDelta_b/xDelta_b;  
    double centerX = (aSlope*bSlope*(p1.getY() - p3.getY()) + bSlope*(p1.getX() + p2.getX())
                        - aSlope*(p2.getX()+p3.getX()) )/(2* (bSlope-aSlope) );
    double centerY = -1*(centerX - (p1.getX()+p2.getX())/2)/aSlope +  (p1.getY()+p2.getY())/2;
    
    center.setLocation(centerX, centerY);
    return center;
  }
  
  
}






