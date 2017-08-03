package test.com.gt.api.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gt.api.dto.ResponseUtils;
import com.gt.api.enums.ResponseEnums;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试类
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/08/02
 * @since 1.0.0
 */
public class ResponseUtilsTest {

    public static final Logger LOGGER = LoggerFactory.getLogger( ResponseUtilsTest.class );

    @Test
    public void responseTest() throws JsonProcessingException {
	ObjectMapper om = new ObjectMapper();
	om.setSerializationInclusion( JsonInclude.Include.NON_NULL );
	LOGGER.info( "成功数据格式：{}", om.writeValueAsString( ResponseUtils.createBySuccess() ) );
	LOGGER.info( "成功数据格式+定制消息： {}", om.writeValueAsString( ResponseUtils.createBySuccessMessage( "成功获取" ) ) );
	LOGGER.info( "成功数据格式+返回泛型数据：{}", om.writeValueAsString( ResponseUtils.createBySuccess( "XXXUser" ) ) );
	LOGGER.info( "成功数据格式+定制消息+返回泛型数据：{}", om.writeValueAsString( ResponseUtils.createBySuccess( "获取到数据了", "XXXUser" ) ) );
	LOGGER.info( "成功数据格式+定制状态码+定制消息+返回泛型数据：{}",
			om.writeValueAsString( ResponseUtils.createBySuccessCodeMessage( ResponseEnums.SUCCESS.getCode(), ResponseEnums.SUCCESS.getMsg(), "XXXUser" ) ) );
	LOGGER.info( "失败数据格式：{}", om.writeValueAsString( ResponseUtils.createByError() ) );
	LOGGER.info( "失败数据格式+定制消息：{}", om.writeValueAsString( ResponseUtils.createByErrorMessage( "参数缺失" ) ) );
	LOGGER.info( "失败数据格式+定制状态码+定制消息：{}", om.writeValueAsString( ResponseUtils.createByErrorCodeMessage( ResponseEnums.SYSTEM_ERROR.getCode(), ResponseEnums.SYSTEM_ERROR.getMsg() ) ) );
    }

}
