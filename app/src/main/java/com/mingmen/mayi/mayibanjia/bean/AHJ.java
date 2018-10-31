package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/18.
 */

public class AHJ {


    /**
     * ERRORNO : 0
     * GRID0 : {"code":0,"message":"SUCCESS","result":{"compositeIndexGEM":[{"rate":"-0.00222918","tradeDate":"20180502"},{"rate":"0.01173907","tradeDate":"20180503"},{"rate":"0.00512795","tradeDate":"20180504"},{"rate":"0.02588620","tradeDate":"20180507"},{"rate":"0.02839673","tradeDate":"20180508"},{"rate":"0.02780967","tradeDate":"20180509"},{"rate":"0.03309878","tradeDate":"20180510"},{"rate":"0.01616309","tradeDate":"20180511"},{"rate":"0.01405908","tradeDate":"20180514"},{"rate":"0.02902977","tradeDate":"20180515"},{"rate":"0.02275095","tradeDate":"20180516"},{"rate":"0.01418314","tradeDate":"20180517"},{"rate":"0.01725636","tradeDate":"20180518"},{"rate":"0.03150485","tradeDate":"20180521"},{"rate":"0.03899213","tradeDate":"20180522"},{"rate":"0.02236271","tradeDate":"20180523"},{"rate":"0.01816963","tradeDate":"20180524"},{"rate":"-0.00057986","tradeDate":"20180525"}],"clientAccumulativeRate":[{"tradeDate":"20180502","value":0.03676598},{"tradeDate":"20180503","value":0.04389086},{"tradeDate":"20180504","value":0.02586678},{"tradeDate":"20180507","value":0.06254981},{"tradeDate":"20180508","value":0.08384138},{"tradeDate":"20180509","value":0.05759211},{"tradeDate":"20180510","value":0.06473651},{"tradeDate":"20180511","value":0.06764582},{"tradeDate":"20180514","value":0.08505964},{"tradeDate":"20180515","value":0.09219111},{"tradeDate":"20180516","value":0.06195802},{"tradeDate":"20180517","value":0.08178629},{"tradeDate":"20180518","value":0.06189841},{"tradeDate":"20180521","value":0.08498741},{"tradeDate":"20180522","value":0.14887381},{"tradeDate":"20180523","value":0.15332169},{"tradeDate":"20180524","value":0.18007851},{"tradeDate":"20180525","value":0.19270683}],"compositeIndexShanghai":[{"rate":"-0.00034196","tradeDate":"20180502"},{"rate":"0.00604335","tradeDate":"20180503"},{"rate":"0.00285572","tradeDate":"20180504"},{"rate":"0.01765377","tradeDate":"20180507"},{"rate":"0.02571709","tradeDate":"20180508"},{"rate":"0.02495562","tradeDate":"20180509"},{"rate":"0.02990723","tradeDate":"20180510"},{"rate":"0.02629005","tradeDate":"20180511"},{"rate":"0.02978395","tradeDate":"20180514"},{"rate":"0.03565177","tradeDate":"20180515"},{"rate":"0.02833467","tradeDate":"20180516"},{"rate":"0.02337625","tradeDate":"20180517"},{"rate":"0.03603623","tradeDate":"20180518"},{"rate":"0.04269927","tradeDate":"20180521"},{"rate":"0.04286441","tradeDate":"20180522"},{"rate":"0.02813968","tradeDate":"20180523"},{"rate":"0.02349564","tradeDate":"20180524"},{"rate":"0.01916534","tradeDate":"20180525"}],"compositeIndexShenzhen":[{"rate":"0.00178004","tradeDate":"20180502"},{"rate":"0.01299389","tradeDate":"20180503"},{"rate":"0.00985242","tradeDate":"20180504"},{"rate":"0.02925488","tradeDate":"20180507"},{"rate":"0.03712899","tradeDate":"20180508"},{"rate":"0.03531456","tradeDate":"20180509"},{"rate":"0.03925607","tradeDate":"20180510"},{"rate":"0.03000959","tradeDate":"20180511"},{"rate":"0.03360851","tradeDate":"20180514"},{"rate":"0.04102158","tradeDate":"20180515"},{"rate":"0.03650125","tradeDate":"20180516"},{"rate":"0.03012562","tradeDate":"20180517"},{"rate":"0.03371167","tradeDate":"20180518"},{"rate":"0.04270245","tradeDate":"20180521"},{"rate":"0.04274681","tradeDate":"20180522"},{"rate":"0.02970187","tradeDate":"20180523"},{"rate":"0.02321282","tradeDate":"20180524"},{"rate":"0.01198677","tradeDate":"20180525"}]}}
     */

    private int ERRORNO;
    private GRID0Bean GRID0;

    public int getERRORNO() {
        return ERRORNO;
    }

    public void setERRORNO(int ERRORNO) {
        this.ERRORNO = ERRORNO;
    }

    public GRID0Bean getGRID0() {
        return GRID0;
    }

    public void setGRID0(GRID0Bean GRID0) {
        this.GRID0 = GRID0;
    }

    /**
     * 我的收益
     */
    public static class IncomeBean {
        /**
         * tradeDate : 20180502
         * value : 0.03676598
         */
        private String tradeDate;
        private double value;



        public String getTradeDate() {
            return tradeDate;
        }

        public void setTradeDate(String tradeDate) {
            this.tradeDate = tradeDate;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }

    public static class GRID0Bean {
        /**
         * code : 0
         * message : SUCCESS
         * result : {"compositeIndexGEM":[{"rate":"-0.00222918","tradeDate":"20180502"},{"rate":"0.01173907","tradeDate":"20180503"},{"rate":"0.00512795","tradeDate":"20180504"},{"rate":"0.02588620","tradeDate":"20180507"},{"rate":"0.02839673","tradeDate":"20180508"},{"rate":"0.02780967","tradeDate":"20180509"},{"rate":"0.03309878","tradeDate":"20180510"},{"rate":"0.01616309","tradeDate":"20180511"},{"rate":"0.01405908","tradeDate":"20180514"},{"rate":"0.02902977","tradeDate":"20180515"},{"rate":"0.02275095","tradeDate":"20180516"},{"rate":"0.01418314","tradeDate":"20180517"},{"rate":"0.01725636","tradeDate":"20180518"},{"rate":"0.03150485","tradeDate":"20180521"},{"rate":"0.03899213","tradeDate":"20180522"},{"rate":"0.02236271","tradeDate":"20180523"},{"rate":"0.01816963","tradeDate":"20180524"},{"rate":"-0.00057986","tradeDate":"20180525"}],"clientAccumulativeRate":[{"tradeDate":"20180502","value":0.03676598},{"tradeDate":"20180503","value":0.04389086},{"tradeDate":"20180504","value":0.02586678},{"tradeDate":"20180507","value":0.06254981},{"tradeDate":"20180508","value":0.08384138},{"tradeDate":"20180509","value":0.05759211},{"tradeDate":"20180510","value":0.06473651},{"tradeDate":"20180511","value":0.06764582},{"tradeDate":"20180514","value":0.08505964},{"tradeDate":"20180515","value":0.09219111},{"tradeDate":"20180516","value":0.06195802},{"tradeDate":"20180517","value":0.08178629},{"tradeDate":"20180518","value":0.06189841},{"tradeDate":"20180521","value":0.08498741},{"tradeDate":"20180522","value":0.14887381},{"tradeDate":"20180523","value":0.15332169},{"tradeDate":"20180524","value":0.18007851},{"tradeDate":"20180525","value":0.19270683}],"compositeIndexShanghai":[{"rate":"-0.00034196","tradeDate":"20180502"},{"rate":"0.00604335","tradeDate":"20180503"},{"rate":"0.00285572","tradeDate":"20180504"},{"rate":"0.01765377","tradeDate":"20180507"},{"rate":"0.02571709","tradeDate":"20180508"},{"rate":"0.02495562","tradeDate":"20180509"},{"rate":"0.02990723","tradeDate":"20180510"},{"rate":"0.02629005","tradeDate":"20180511"},{"rate":"0.02978395","tradeDate":"20180514"},{"rate":"0.03565177","tradeDate":"20180515"},{"rate":"0.02833467","tradeDate":"20180516"},{"rate":"0.02337625","tradeDate":"20180517"},{"rate":"0.03603623","tradeDate":"20180518"},{"rate":"0.04269927","tradeDate":"20180521"},{"rate":"0.04286441","tradeDate":"20180522"},{"rate":"0.02813968","tradeDate":"20180523"},{"rate":"0.02349564","tradeDate":"20180524"},{"rate":"0.01916534","tradeDate":"20180525"}],"compositeIndexShenzhen":[{"rate":"0.00178004","tradeDate":"20180502"},{"rate":"0.01299389","tradeDate":"20180503"},{"rate":"0.00985242","tradeDate":"20180504"},{"rate":"0.02925488","tradeDate":"20180507"},{"rate":"0.03712899","tradeDate":"20180508"},{"rate":"0.03531456","tradeDate":"20180509"},{"rate":"0.03925607","tradeDate":"20180510"},{"rate":"0.03000959","tradeDate":"20180511"},{"rate":"0.03360851","tradeDate":"20180514"},{"rate":"0.04102158","tradeDate":"20180515"},{"rate":"0.03650125","tradeDate":"20180516"},{"rate":"0.03012562","tradeDate":"20180517"},{"rate":"0.03371167","tradeDate":"20180518"},{"rate":"0.04270245","tradeDate":"20180521"},{"rate":"0.04274681","tradeDate":"20180522"},{"rate":"0.02970187","tradeDate":"20180523"},{"rate":"0.02321282","tradeDate":"20180524"},{"rate":"0.01198677","tradeDate":"20180525"}]}
         */

        private int code;
        private String message;
        private ResultBean result;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean {
            private List<CompositeIndexBean> compositeIndexGEM;
            private List<IncomeBean> clientAccumulativeRate;
            private List<CompositeIndexBean> compositeIndexShanghai;
            private List<CompositeIndexBean> compositeIndexShenzhen;

            public List<CompositeIndexBean> getCompositeIndexGEM() {
                return compositeIndexGEM;
            }

            public void setCompositeIndexGEM(List<CompositeIndexBean> compositeIndexGEM) {
                this.compositeIndexGEM = compositeIndexGEM;
            }

            public List<IncomeBean> getClientAccumulativeRate() {
                return clientAccumulativeRate;
            }

            public void setClientAccumulativeRate(List<IncomeBean> clientAccumulativeRate) {
                this.clientAccumulativeRate = clientAccumulativeRate;
            }

            public List<CompositeIndexBean> getCompositeIndexShanghai() {
                return compositeIndexShanghai;
            }

            public void setCompositeIndexShanghai(List<CompositeIndexBean> compositeIndexShanghai) {
                this.compositeIndexShanghai = compositeIndexShanghai;
            }

            public List<CompositeIndexBean> getCompositeIndexShenzhen() {
                return compositeIndexShenzhen;
            }

            public void setCompositeIndexShenzhen(List<CompositeIndexBean> compositeIndexShenzhen) {
                this.compositeIndexShenzhen = compositeIndexShenzhen;
            }

        }
    }


    /**
     * 沪深创指数
     */
    public class CompositeIndexBean {
        /**
         * rate : -0.00034196
         * tradeDate : 20180502
         */
        private String rate;
        private String tradeDate;

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getTradeDate() {
            return tradeDate;
        }

        public void setTradeDate(String tradeDate) {
            this.tradeDate = tradeDate;
        }
    }


    public static String JSON="{\n" +
            "  \"ERRORNO\":0,\n" +
            "  \"GRID0\":{\n" +
            "    \"code\":0,\n" +
            "    \"message\":\"SUCCESS\",\n" +
            "    \"result\":{\n" +
            "      \"compositeIndexGEM\":[\n" +
            "        {\n" +
            "          \"rate\":\"-0.00222918\",\n" +
            "          \"tradeDate\":\"20180502\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01173907\",\n" +
            "          \"tradeDate\":\"20180503\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.00512795\",\n" +
            "          \"tradeDate\":\"20180504\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02588620\",\n" +
            "          \"tradeDate\":\"20180507\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02839673\",\n" +
            "          \"tradeDate\":\"20180508\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02780967\",\n" +
            "          \"tradeDate\":\"20180509\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03309878\",\n" +
            "          \"tradeDate\":\"20180510\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01616309\",\n" +
            "          \"tradeDate\":\"20180511\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01405908\",\n" +
            "          \"tradeDate\":\"20180514\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02902977\",\n" +
            "          \"tradeDate\":\"20180515\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02275095\",\n" +
            "          \"tradeDate\":\"20180516\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01418314\",\n" +
            "          \"tradeDate\":\"20180517\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01725636\",\n" +
            "          \"tradeDate\":\"20180518\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03150485\",\n" +
            "          \"tradeDate\":\"20180521\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03899213\",\n" +
            "          \"tradeDate\":\"20180522\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02236271\",\n" +
            "          \"tradeDate\":\"20180523\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01816963\",\n" +
            "          \"tradeDate\":\"20180524\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"-0.00057986\",\n" +
            "          \"tradeDate\":\"20180525\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"clientAccumulativeRate\":[\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180502\",\n" +
            "          \"value\":5.5\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180503\",\n" +
            "          \"value\":5.6\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180504\",\n" +
            "          \"value\":5.7\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180507\",\n" +
            "          \"value\":6.8\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180508\",\n" +
            "          \"value\":3.8\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180509\",\n" +
            "          \"value\":4.2\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180510\",\n" +
            "          \"value\":5.7\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180511\",\n" +
            "          \"value\":2.7\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180514\",\n" +
            "          \"value\":7.8\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180515\",\n" +
            "          \"value\":5.8\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180516\",\n" +
            "          \"value\":7.6\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180517\",\n" +
            "          \"value\":6.7\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180518\",\n" +
            "          \"value\":2.7\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180521\",\n" +
            "          \"value\":3.7\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180522\",\n" +
            "          \"value\":4.4\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180523\",\n" +
            "          \"value\":5.6\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180524\",\n" +
            "          \"value\":4.5\n" +
            "        },\n" +
            "        {\n" +
            "          \"tradeDate\":\"20180525\",\n" +
            "          \"value\":5.4\n" +
            "        }\n" +
            "      ],\n" +
            "      \"compositeIndexShanghai\":[\n" +
            "        {\n" +
            "          \"rate\":\"-0.00034196\",\n" +
            "          \"tradeDate\":\"20180502\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.00604335\",\n" +
            "          \"tradeDate\":\"20180503\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.00285572\",\n" +
            "          \"tradeDate\":\"20180504\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01765377\",\n" +
            "          \"tradeDate\":\"20180507\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02571709\",\n" +
            "          \"tradeDate\":\"20180508\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02495562\",\n" +
            "          \"tradeDate\":\"20180509\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02990723\",\n" +
            "          \"tradeDate\":\"20180510\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02629005\",\n" +
            "          \"tradeDate\":\"20180511\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02978395\",\n" +
            "          \"tradeDate\":\"20180514\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03565177\",\n" +
            "          \"tradeDate\":\"20180515\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02833467\",\n" +
            "          \"tradeDate\":\"20180516\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02337625\",\n" +
            "          \"tradeDate\":\"20180517\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03603623\",\n" +
            "          \"tradeDate\":\"20180518\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.04269927\",\n" +
            "          \"tradeDate\":\"20180521\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.04286441\",\n" +
            "          \"tradeDate\":\"20180522\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02813968\",\n" +
            "          \"tradeDate\":\"20180523\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02349564\",\n" +
            "          \"tradeDate\":\"20180524\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01916534\",\n" +
            "          \"tradeDate\":\"20180525\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"compositeIndexShenzhen\":[\n" +
            "        {\n" +
            "          \"rate\":\"0.00178004\",\n" +
            "          \"tradeDate\":\"20180502\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01299389\",\n" +
            "          \"tradeDate\":\"20180503\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.00985242\",\n" +
            "          \"tradeDate\":\"20180504\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02925488\",\n" +
            "          \"tradeDate\":\"20180507\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03712899\",\n" +
            "          \"tradeDate\":\"20180508\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03531456\",\n" +
            "          \"tradeDate\":\"20180509\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03925607\",\n" +
            "          \"tradeDate\":\"20180510\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03000959\",\n" +
            "          \"tradeDate\":\"20180511\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03360851\",\n" +
            "          \"tradeDate\":\"20180514\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.04102158\",\n" +
            "          \"tradeDate\":\"20180515\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03650125\",\n" +
            "          \"tradeDate\":\"20180516\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03012562\",\n" +
            "          \"tradeDate\":\"20180517\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.03371167\",\n" +
            "          \"tradeDate\":\"20180518\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.04270245\",\n" +
            "          \"tradeDate\":\"20180521\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.04274681\",\n" +
            "          \"tradeDate\":\"20180522\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02970187\",\n" +
            "          \"tradeDate\":\"20180523\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.02321282\",\n" +
            "          \"tradeDate\":\"20180524\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rate\":\"0.01198677\",\n" +
            "          \"tradeDate\":\"20180525\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
