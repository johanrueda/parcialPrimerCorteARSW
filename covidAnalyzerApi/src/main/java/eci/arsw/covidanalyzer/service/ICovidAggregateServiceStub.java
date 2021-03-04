package eci.arsw.covidanalyzer.service;


import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("ICovidAggregateServiceStub")
public class ICovidAggregateServiceStub implements  ICovidAggregateService{
    private List<Result> List = new CopyOnWriteArrayList<>();

    public ICovidAggregateServiceStub(){

        List.add(new Result(UUID.randomUUID(),"Johan","Rueda","j.@gmail.com","2021-03-03T10:65:45Z",true,1.5,ResultType.TRUE_POSITIVE ));
        List.add(new Result(UUID.randomUUID(),"david","Rueda","j.@gmail.com","2021-03-03T10:65:45Z",false,1.5,ResultType.TRUE_NEGATIVE ));
        List.add(new Result(UUID.randomUUID(),"diego","Rueda","j.@gmail.com","2021-03-03T10:65:45Z",true,1.5,ResultType.FALSE_POSITIVE ));
        List.add(new Result(UUID.randomUUID(),"german","Rueda","j.@gmail.com","2021-03-03T10:65:45Z",false,1.5,ResultType.TRUE_NEGATIVE ));
    }


    @Override
    public void aggregateResult(Result result, ResultType type) throws Excepcion{
      try{
          if(result.getResultType() == type){
              List.add(result);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

    /**
     * Get all the results for the specified result type.
     *
     * @param type
     * @return
     */
    @Override
    public List<Result> getResult(ResultType type) {
        List<Result> test=new CopyOnWriteArrayList<>();
        for (Result result:List)
        {
            if(result.getResultType().equals(type))
            {
                test.add(result);
            }
        }
        return test;
    }

    /**
     * @param id
     * @param type
     * @return
     */
    @Override
    public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
        for (Result result:List) {
            if (result.getId().equals(id)){
                result.setResultType(type);
            }
        }
    }
}
