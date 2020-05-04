package com.foryou.security.core.generator;

import com.foryou.security.core.validate.code.ImageCode;
import com.foryou.security.core.validate.code.ValidateCode;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;

//@Component
public class ImageCodeGenerator extends AbstractValidateCodeGenerator {
    // 图片的宽度
    private int width;
    // 图片的高度
    private int height;

    /**
     * 参数初始化
     */
    public void init(ServletWebRequest request) {
        width = ServletRequestUtils.getIntParameter(request.getRequest(),"width", securityProperties.getValidateCode().getImageCode().getWidth());
        height = ServletRequestUtils.getIntParameter(request.getRequest(),"height", securityProperties.getValidateCode().getImageCode().getHeight());
        codeCount = securityProperties.getValidateCode().getImageCode().getCodeCount();
        expireIn = securityProperties.getValidateCode().getImageCode().getExpireIn();
    }

    /**
     * 创建并获取图片
     *
     * @return
     */
    @Override
    public ValidateCode generator(ServletWebRequest request) {
        //参数初始化
        init(request);
        //  字体的高度
        int fontHeight = height * 3 / 4;
        // 每个字符的宽度
        int codeX = width / (codeCount + codeCount / 2);

        // 图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 微软雅黑 粗体显示 图片高度的3/4
        Font font = new Font("微软雅黑", Font.CENTER_BASELINE, fontHeight);
        g.setFont(font);

        // 生成随机验证码
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 设置字体颜色
            g.setColor(getRandomColor());
            // 设置字体位置
            g.drawString(strRand, (i + 1) * codeX, fontHeight + getRandomNumber(height / 8));
            randomCode.append(strRand);
        }
        return new ImageCode(buffImg, randomCode.toString(), expireIn);
    }

    /**
     * 获取随机颜色
     */
    private Color getRandomColor() {
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r, g, b);
    }

}
