package amazon.oa.y2017;

import java.util.ArrayList;
import java.util.List;
/**
 * In a company which has CEO Bill and a hierarchy of employees. 
 * Employees can have a list of other employees reporting to them, 
 * which can themselves have reports, and so on. 
 * An employee with at least one report is called a manager.
Please implement the closestCommonManager method to preorderFind the closest manager
(i.e. farthest from the CEO) to two employees. 
You may assume that all employees eventually report up to the CEO.
 * @author Andrew
 *
 */
class Employee {
	 
    final int id;
    final String name;
    final List<Employee> reports;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.reports = new ArrayList<Employee>();
    }
}

public class ClosestCommonManager {
	
	private static boolean DFSFindPath(Employee root, Employee target, List<Employee> path){
		path.add(root);//adding current node to path.lat one is employee itself
		if(root.id == target.id){
			path.remove(root);//not including itself. remove this if one can be its own manager
			return true;
		}
		
		for(Employee r : root.reports){
			boolean res = DFSFindPath(r, target, path);
			if(res){
				return true;//finding one, finish earlier
			}
			//if not finding one from a child node, continue to next child node
		}
		//not finding a path from current node, remove it from path
		path.remove(root);
		//and return false
		return false;
	}
	public static Employee closestCommonManager(Employee ceo, Employee firstEmployee, Employee secondEmployee) {
		List<Employee> firstPath = new ArrayList<Employee>();
		List<Employee> secondPath = new ArrayList<Employee>();
		
		Employee root = ceo;
		
		DFSFindPath(root, firstEmployee, firstPath);
		DFSFindPath(root, secondEmployee, secondPath);
		//ceo is excluded from the answer
		//pick any path to examine. here we pick first path. try to preorderFind the first difference in them and return the previous one as lowest common manager
		for(int i=1;i<firstPath.size();i++){
			if(firstPath.get(i).id!=secondPath.get(i).id)
				return firstPath.get(i-1)==ceo?null:firstPath.get(i-1);
		}
		//no difference was found, return firstPath's last element. checking if it is ceo
	    return firstPath.get(firstPath.size()-1)==ceo?null:firstPath.get(firstPath.size()-1);
	}
}
