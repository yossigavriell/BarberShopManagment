/**
 * @author ${Yossef Hai Gavriel, Omer Pesah}
 */

package il.ac.hit;

import il.ac.hit.model.DataBaseModel;
import il.ac.hit.viewmodel.Management;

public class main {

    public static void main(String[] args)
    {
        DataBaseModel model = DataBaseModel.getInstance();
        Management.getManage().setModel(model);
        Management.getManage().startProgram();

    }
}
