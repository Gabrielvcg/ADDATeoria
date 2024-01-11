package transformaciones;

import java.util.stream.Stream;

public class Ej1 {

	
	//APARTADO A: REC NO FINAL
	
	public static String apartadoA(Integer a, Integer b, Integer c , String d) {
		String ac;
		if(a<3 && b<3) {
			ac=c.toString();
		}else if(a<3 && b<=3) {
			ac="~"+apartadoA(a-1,b-1,c+4,d);
		}else if(a>=3 && b<3) {
			ac="-"+c.toString()+"-"+apartadoA(a-2,b-1,c,d+"x");
		}else {
			ac="/"+d+"/" + apartadoA(a-1,b-2,c+1,d);
		}
		return ac;
	}
	
	//APARTADO B: REC FINAL
	
	public static String apartadoB(Integer a, Integer b, Integer c, String d) {
		return apartadoBAux(a,b,c,d,"");
	}
	
	public static String apartadoBAux(Integer a, Integer b, Integer c,String d, String ac) {
		if(a<3 && b<3) {
			ac=ac+c.toString();
		}else if(a<3 && b<=3) {
			ac="~"+ac;
			ac=apartadoBAux(a-1,b-1,c+4,d,ac);
		}else if(a>=3 && b<3) {
			ac="-"+c.toString()+"-"+ac;
			ac=apartadoBAux(a-2,b-1,c,d+"x",ac);
		}else {
			ac="/"+d+"/"+ac;
			ac=apartadoBAux(a-1,b-2,c+1,d,ac);
		}
		return ac;
	
	}
	
	//APARTADO C ITERATIVO
	
	public static String apartadoC(Integer a,Integer b, Integer c, String d) {
		String ac="";
		while (!(a<3 && b<3)) {
			
			 if(a<3 && b<=3) {
				ac="~"+ac;
				a-=1; b-=1;c+=4;
				
			}else if(a>=3 && b<3) {
				ac="-"+c.toString()+"-"+ac;
				a-=2;b-=1;d+="x";
			}else {
				
				ac="/"+d+"/"+ac;
				a-=1;b-=2;c+=1;
			}
		}
		ac+=c.toString();
		return ac;
	}
	
	//APARTADO D SOLUCION FUNCIONAL
	
	record Tupla (Integer a, Integer b, Integer c,String d, String ac) {
		public static Tupla of(Integer a, Integer b, Integer c, String d, String ac) {
			return new Tupla(a,b,c,d,ac);
		}
		public static Tupla next(Tupla t) {
			Tupla res;
			 if(t.a<3 && t.b<=3) {
				 	res= Tupla.of(t.a-1, t.b-1, t.c+4, t.d, "~"+t.ac);
					
				}else if(t.a>=3 && t.b<3) {
				 	 res=Tupla.of(t.a-2, t.b-1, t.c, t.d+"x", "-"+t.c.toString()+"-"+t.ac);

				}else {
				 	 res= Tupla.of(t.a-1, t.b-2, t.c+1, t.d, "/"+t.d+"/"+t.ac);

				}return res;
		}
	}
	public static String apartadoD(Integer a, Integer b, Integer c,String d) {
		Tupla t=Tupla.of(a, b, c, d, "");
		Tupla res= Stream.iterate(t, e->Tupla.next(e)).filter(e->e.a<3 && e.b<3).findFirst().get();
		return res.ac+res.c.toString();
	}
	
	public static void main(String[] args) {

		System.out.println("APARTADO A");
		String resA=apartadoA(3,4,4,"z");
		System.out.println(resA);
		
		System.out.println("APARTADO B");
		String resB=apartadoB(3,4,4,"z");
		System.out.println(resB);
		
		System.out.println("APARTADO C");
		String resC=apartadoC(3,4,4,"z");
		System.out.println(resC);
		
		System.out.println("APARTADO D");
		String resD=apartadoD(3,4,4,"z");
		System.out.println(resD);
	}

}
