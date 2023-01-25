package my.test_task.grid;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import my.test_task.AddressDao;
import my.test_task.entities.Address;

@Stateless
public class AddressListController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	private ListModel<Address> addresesModel;
	
	@Inject
	private AddressDao addressDao;

	public AddressListController() {
		
	}
	
	@PostConstruct
	public void init() {
		addresesModel = new ListModelList<Address>(addressDao.findAll()); 
	}

	public ListModel<Address> getAddressesModel() {
		return addresesModel;
	}
}
