package com.telefonica.hispam.routerInventory.application.ports.output;

import java.util.List;

import com.telefonica.hispam.routerInventory.domain.entity.Router;
import com.telefonica.hispam.routerInventory.domain.vo.Id;

public interface RouterMgmtOutputPort {

    public Router persistRouter(Router router);
      
    public void retireRouter(Id id);

    public Router retrieveRouter(Id id);

    public boolean existsRouter(Id id);

    public List<Router> listRouters();
    
}
