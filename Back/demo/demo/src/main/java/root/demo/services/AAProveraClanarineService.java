package root.demo.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import root.demo.model.Casopis;
import root.demo.model.Clanarina;
import root.demo.model.FormSubmissonDTO;
import root.demo.model.Korisnik;
import root.demo.repository.CasopisRepository;
import root.demo.repository.ClanarinaRepository;
import root.demo.repository.KorisnikRepository;

@Service
public class AAProveraClanarineService implements JavaDelegate{
	@Autowired
	IdentityService identityService;
	
	@Autowired
	KorisnikRepository korisnikRepository ;
	
	@Autowired
	ClanarinaRepository clanarinaRepository ;
	
	@Autowired
	CasopisRepository casopisRepository ;


	@Override
	public void execute(DelegateExecution execution) throws Exception 
	{
		// TODO Auto-generated method stub
		
		Korisnik k = new Korisnik();
		k = getCurrentUser();
		
		if (k == null) // niko nije ulogovan
		{
			execution.setVariable("placenaClanarinaVar", false);
		}
		
		List<Clanarina> sveClanarine = clanarinaRepository.findAll();
		
		if (sveClanarine.size() == 0) // ne postoji nijedna clanarina
		{
			execution.setVariable("placenaClanarinaVar", false);
			System.out.println("Postavljena je placenaClanarinaVar na false, jer nema clanarina!");

		}
		
		execution.setVariable("placenaClanarinaVar", false);
		List<FormSubmissonDTO> izabranCasopis = (List<FormSubmissonDTO>)execution.getVariable("izabranCasopis");
		
		 Casopis casopis = new Casopis();

		 for(FormSubmissonDTO item: izabranCasopis)
		  {
			  String fieldId=item.getFieldId();
			  
			 if(fieldId.equals("casopisiL")){
				  
				  List<Casopis> allCasopisi = casopisRepository.findAll();
				  for(Casopis c : allCasopisi){
					  for(String selectedEd:item.getCategories())
					  {
						  String idS= c.getId().toString();
						  
						  if(idS.equals(selectedEd)){
							  casopis = casopisRepository.findOneByIssn(c.getIssn());
							  break ;
							  
						  }
					  }
				  }
			 }
			 
			}	// nasla sam sacuvan casopis			 
		
		
		for (Clanarina cl: sveClanarine)
		{
			if (cl.getKorisnik().getId().equals(k.getId()) && cl.getCasopis().getId().equals(casopis.getId())) 
			// pronasao sam da je korisnik platio clanarinu za dati casopis
			{
				execution.setVariable("placenaClanarinaVar", true);
				System.out.println("Postavljena je placenaClanarinaVar na true, jer je nasao da je placena!");
				break ;
			}
		}
		
	}
	
	// SPRING SECURITY - VRATI TRENUTNOG KORISNIKA
	public Korisnik getCurrentUser() {
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				System.out.println("Principal.toString() je: " + principal.toString());
				Korisnik k = korisnikRepository.findOneByUsername(principal.toString());
				if (k != null) 
				{
					String kIme = k.getUsername();
					System.out.println("Username getCurrentUser je: " + kIme);
				}
				else 
				{
					System.out.println("Korisnik je null!");
				}
				
				
				return k;
	}	


}
