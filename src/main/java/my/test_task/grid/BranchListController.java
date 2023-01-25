package my.test_task.grid;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import my.test_task.BranchDao;
import my.test_task.entities.Branch;

@Stateless
public class BranchListController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	private ListModel<Branch> branchesModel;
	
	@Inject
	private BranchDao branchDao;

	public BranchListController() {
		
	}
	
	@PostConstruct
	public void init() {
		branchesModel = new ListModelList<Branch>(branchDao.findAll()); 
	}

	public ListModel<Branch> getBranchesModel() {
		return branchesModel;
	}
	
}
