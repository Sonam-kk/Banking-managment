 class  Interface{
	public static void main(String [] args){
		Uplifter sonam =new Uplifter("Monika");
		sonam.Study();
		sonam.work();
		NGStudent ponam=new Uplifter("Aanjali");
		ponam.Study();
		Accenterintern vanshika=new Accenterintern("Monika");
		vanshika.work();
		vanshika.Study();
		System.out.println("Congrats:"+vanshika.getName());
		
	}
}
interface NGStudent{
	public void Study();
}
interface NWintern{
	public void work();
}
interface Accintern{
	public void work();
}
abstract class DefaultNGStudents implements NGStudent{
	private String name;
	DefaultNGStudents(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

}
class Uplifter extends DefaultNGStudents implements NWintern{
	Uplifter(String  name){
		super(name);
	}
	public void Study(){
		System.out.println("I study at Navgurukul");
	}
	public void work(){
		System.out.println("I work at Natwest");
	}

}
class Accenterintern  extends DefaultNGStudents implements Accintern{
	Accenterintern(String  name){
		super(name);
	}
	public void Study(){
		System.out.println("I study at Navgurukul");
	}
	public void work(){
		System.out.println("I work at Accenture");
	}

}
