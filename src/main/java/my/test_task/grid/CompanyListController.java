package my.test_task.grid;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import my.test_task.CompanyDao;
import my.test_task.entities.Company;

@Stateless
public class CompanyListController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	private ListModel<Company> companiesModel;
	
	@Inject
	private CompanyDao companyDao;

	public CompanyListController() {
		
	}
	
	@PostConstruct
	public void init() {
		companiesModel = new ListModelList<Company>(companyDao.findAll()); 
	}

	public ListModel<Company> getCompaniesModel() {
		return companiesModel;
	}
}
