package amazon.medium;

import java.util.*;

/**
 * 1.build a customer purchase records just for one customer. make method to return its suggestions
 * 2.build revert index of product+rate-->list<customer>. call it productRateAndCustomers
 * 3.use productRateAndCustomers to figure out all similiar customer of given customer
 * 4.collect all similar customer's suggestions--exclude the one customer already like or dislike
 * Created by andrew on 6/6/2017.
 */
public class ProductionRecommendation {
    //PurchaseRecord probably is rawer than this, but they should be converted to customer purchase records
    /**
     * customer purchase record,
     */
    static class CustomerPurchaseRecord{
        String customer;
        Map<String,Integer> productAndRates = new HashMap<>();
        List<String> getMyRate5Product(){
            List<String> mySuggestions = new ArrayList<>();
            for(Map.Entry<String,Integer> e:productAndRates.entrySet()){
                if(e.getValue()==5)mySuggestions.add(e.getKey());
            }
            return mySuggestions;
        }
        List<String> getMyDislikedProduct(){
            List<String> myDislikes = new ArrayList<>();
            for(Map.Entry<String,Integer> e:productAndRates.entrySet()){
                if(e.getValue()<4)myDislikes.add(e.getKey());
            }
            return myDislikes;
        }
    }
    //customers that have same purchase and same rating
    //key is productRn
    Map<String,Set<String>> productAndCustomer = new HashMap<>();
    List<CustomerPurchaseRecord> purchaseRecords;
    public ProductionRecommendation(List<CustomerPurchaseRecord> pr){
        purchaseRecords = pr;
        //build up an in memory database of inverted index of product rating and customers
        for(CustomerPurchaseRecord cp:pr){
            for(Map.Entry<String,Integer> e:cp.productAndRates.entrySet()){
                String productRate = e.getKey()+"R"+e.getValue();
                if(productAndCustomer.containsKey(productRate)){
                    productAndCustomer.get(productRate).add(cp.customer);
                }else{
                    Set<String> customers = new HashSet<>();
                    customers.add(cp.customer);
                    productAndCustomer.put(productRate,customers);
                }
            }
        }
    }
    private CustomerPurchaseRecord findCustomerPurchaseRecord(String customer){
        for(CustomerPurchaseRecord pr:purchaseRecords){
            if (customer.equals(pr.customer))return pr;
        }
        return null;
    }
    public Set<String> findRecommendation(String customer){
        //find customer purchase history
        CustomerPurchaseRecord pr = findCustomerPurchaseRecord(customer);
        //find customers with same purchase ratings
        Set<String> similarCustomers = new HashSet<>();
        for(Map.Entry<String,Integer> e:pr.productAndRates.entrySet()){
            similarCustomers.addAll(productAndCustomer.get(e.getKey()+"R"+e.getValue()));
        }
        similarCustomers.remove(customer);
        Set<String> suggestions = new HashSet<>();
        CustomerPurchaseRecord tpr;
        for(String sc:similarCustomers){
            tpr = findCustomerPurchaseRecord(sc);
            suggestions.addAll(tpr.getMyRate5Product());
        }
        suggestions.removeAll(pr.getMyRate5Product());
        suggestions.removeAll(pr.getMyDislikedProduct());
        return suggestions;
    }
}
