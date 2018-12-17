package com.yxb.server.utils;

import com.yxb.server.VO.ResultVO;

/**
 * Created by 廖师兄
 * 2017-12-09 22:53
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(500);
        resultVO.setMsg("失败");
        return resultVO;
    }
    public static ResultVO error() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(500);
        resultVO.setMsg("失败");
        return resultVO;
    }


}
