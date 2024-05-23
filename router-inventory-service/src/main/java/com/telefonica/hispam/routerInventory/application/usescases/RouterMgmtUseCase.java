package com.telefonica.hispam.routerInventory.application.usescases;

import java.util.List;

import com.telefonica.hispam.routerInventory.domain.entity.Router;
import com.telefonica.hispam.routerInventory.domain.vo.Id;

public interface RouterMgmtUseCase {  
      
    public Router addRouter(Router router);

    public void retireRouter(Id id);

    public Router retrieveRouter(Id id);

    public Router persistRouter(Router router);

    public boolean existsRouter(Id id);

    public List<Router> listCoreRouters();
    
}