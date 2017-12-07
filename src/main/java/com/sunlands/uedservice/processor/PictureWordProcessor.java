package com.sunlands.uedservice.processor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sunlands.uedservice.bean.ResultBean;
import com.sunlands.uedservice.mapper.AllDao;
import com.sunlands.uedservice.po.PictureWord;
import com.sunlands.uedservice.po.PublishHistory;
import com.sunlands.uedservice.utils.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author : lvpenghui
 * @Description :
 * @Date : Created in 18:28 2017/12/6
 * @ModifiedBy :
 */
public class PictureWordProcessor {

    private static Logger logger = LoggerFactory.getLogger(PictureWord.class);
    private static JsonParser jsonParser = new JsonParser();

    public ResultBean insert(String param) {
        String pictureUrl;
        Byte type;
        String title;
        String article;
        Integer sequence = 0;
        JsonObject pictureWordJson;
        ResultBean pictureWordBean = new ResultBean();
        Long id = SnowflakeIdWorker.getSnowFlakeId();
        try {
            pictureWordJson = (JsonObject) jsonParser.parse(param);
            pictureUrl = pictureWordJson.get("pictureUrl").getAsString();
            type = pictureWordJson.get("type").getAsByte();
            title = pictureWordJson.get("title").getAsString();
            article = pictureWordJson.get("article").getAsString();
            if (pictureWordJson.has("sequence")) {
                sequence = pictureWordJson.get("sequence").getAsInt();
            }
        } catch (Exception e) {
            logger.error("参数传递异常！");
            pictureWordBean.setCode(0);
            pictureWordBean.setMsg("参数传递异常！");
            return pictureWordBean;
        }

        PictureWord pictureWord = new PictureWord();
        pictureWord.setId(id);
        pictureWord.setSequence(sequence);
        pictureWord.setType(type);
        pictureWord.setPictureUrl(pictureUrl);
        pictureWord.setTitle(title);
        pictureWord.setArticle(article);

        // TODO 需要获取session中的用户
        pictureWord.setUpdater("lvpenghui");
        pictureWord.setCreator("lvpenghui");


        PublishHistory publishHistory = new PublishHistory();
        publishHistory.setId(id);
        publishHistory.setPictureUrl(pictureUrl);
        publishHistory.setTitle(title);
        publishHistory.setType(type);
        publishHistory.setDownloadTimes(0);
        publishHistory.setTableChoose("tb_picture_word");
        publishHistory.setDeleteFlag((byte) 0);

        try {
            AllDao.getInstance().getPictureWordDao().insertOne(pictureWord);
            AllDao.getInstance().getPublishHistoryDao().insertOne(publishHistory);
        } catch (Exception e) {
            pictureWordBean.setCode(0);
            pictureWordBean.setMsg("数据插入失败！");
            return pictureWordBean;
        }
        pictureWordBean.setCode(1);
        pictureWordBean.setData(AllDao.getInstance().getPictureWordDao().selectById(id));
        pictureWordBean.setMsg("数据插入成功！");
        return pictureWordBean;
    }

    /**
     * 获取单条记录
     *
     * @param param
     * @return
     */
    public ResultBean getById(String param) {

        ResultBean pictureWordBean = new ResultBean();
        Long id;
        try {
            id = ((JsonObject) jsonParser.parse(param)).get("id").getAsLong();
        } catch (Exception e) {
            pictureWordBean.setCode(0);
            logger.error("参数传递异常！");
            pictureWordBean.setMsg("参数传递异常！");
            return pictureWordBean;
        }
        PictureWord pictureWord = AllDao.getInstance().getPictureWordDao().selectById(id);
        pictureWordBean.setData(pictureWord);
        pictureWordBean.setMsg("数据获取成功！");
        pictureWordBean.setCode(1);
        return pictureWordBean;
    }

    public ResultBean getAllByPageNum(String param) {
        ResultBean pictureWordBean = new ResultBean();
        int pageNum;
        try {
            pageNum = ((JsonObject) jsonParser.parse(param)).get("pageNum").getAsInt();
        } catch (Exception e) {
            logger.error("参数传递异常！");
            pictureWordBean.setCode(0);
            pictureWordBean.setMsg("参数传递异常！");
            return pictureWordBean;
        }
        List<PictureWord> pictureWordList = AllDao.getInstance().getPictureWordDao().getAllByPageNum((pageNum - 1) * 12, pageNum * 12);
        pictureWordBean.setData(pictureWordList);
        pictureWordBean.setMsg("数据获取成功！");
        pictureWordBean.setCode(1);
        return pictureWordBean;
    }
}