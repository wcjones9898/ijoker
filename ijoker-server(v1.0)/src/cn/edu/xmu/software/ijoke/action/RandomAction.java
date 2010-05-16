package cn.edu.xmu.software.ijoke.action;

import java.io.ByteArrayInputStream;
import cn.edu.xmu.software.ijoke.utils.RandomNumUtil;

public class RandomAction extends BaseAction{   
    private ByteArrayInputStream inputStream;   
    public String execute() throws Exception{   
        RandomNumUtil rdnu=RandomNumUtil.Instance();   
        this.setInputStream(rdnu.getImage());
        getSession().put("random", rdnu.getString());
        return SUCCESS;   
    }   
    public void setInputStream(ByteArrayInputStream inputStream) {   
        this.inputStream = inputStream;   
    }   
    public ByteArrayInputStream getInputStream() {   
        return inputStream;   
    }
}

