package com.mingmen.mayi.mayibanjia.utils.custom.zixun;

/**
 * Created by Administrator on 2019/4/26.
 */

public interface PageDecorationLastJudge {
    /**

     * Is the last row in one page

     *

     * @param position

     * @return

     */

    boolean isLastRow(int position);



    /**

     * Is the last Colum in one row;

     *

     * @param position

     * @return

     */

    boolean isLastColumn(int position);



    boolean isPageLast(int position);
}
