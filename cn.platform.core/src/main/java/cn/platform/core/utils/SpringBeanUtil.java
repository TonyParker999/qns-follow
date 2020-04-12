package cn.platform.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     *
     * @param name
     *            the name of the bean to retrieve
     * @param clazz
     *            type the bean must match.
     * @return an instance of the bean
     * @see org.springframework.beans.factory.BeanFactory#getBean(String, Class)
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    /**
     *
     * @param name
     *            the name of the bean to retrieve
     * @return an instance of the bean
     * @see org.springframework.beans.factory.BeanFactory#getBean(String)
     */

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     *
     * @param name
     *            the name of the bean to query
     * @return whether a bean with the given name is present
     * @see org.springframework.beans.factory.BeanFactory#containsBean(String)
     */

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     *
     * @param name
     *            the bean name to check for aliases
     * @return the aliases, or an empty array if none
     * @see org.springframework.beans.factory.BeanFactory#getAliases(String)
     */
    public static String[] getAliases(String name) {
        return applicationContext.getAliases(name);
    }

    /**
     * 获取HttpServletRequest
     *
     * @returnk
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取session
     *
     * @return
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static String getContentPath() {

        return getSession().getServletContext().getRealPath("/");
    }

    /**
     *
     * <strong>Title : autowireBean<br></strong>
     * <strong>Description : </strong>自动填充非容器管理对象实例中变量的SPRING依赖<br>
     * <strong>Create on : 2016年7月28日 下午3:07:17<br></strong>
     * <p>
     * @param bean 对象实例
     * void
     * @throws
     * @author JunLin.Yang<br>
     * @version <strong>v1.0.0</strong><br>
     * <br>
     * <strong>修改历史:</strong><br>
     * 修改人 | 修改日期 | 修改描述<br>
     * -------------------------------------------<br>
     * <br>
     * <br>
     */
    public static void autowireBean(Object bean) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);
    }

    // 等同 new 操作，但对象是被spring容器托管的
    public static synchronized void registerBeanDefinition(String beanName, Class<?> clazz, Object[] argValues,
                                                           Map<String, Object> propertyValues, boolean isSingleton) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext
                .getBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        beanDefinitionBuilder.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
        beanDefinitionBuilder
                .setScope(isSingleton ? ConfigurableBeanFactory.SCOPE_SINGLETON : ConfigurableBeanFactory.SCOPE_PROTOTYPE);

        if (null != propertyValues && 0 != propertyValues.size()) {
            for (Map.Entry<String, Object> entry : propertyValues.entrySet()) {
                beanDefinitionBuilder.addPropertyValue(entry.getKey(), entry.getValue());
            }
        }

        if (null != argValues && 0 != argValues.length) {
            for (Object argValue : argValues) {
                beanDefinitionBuilder.addConstructorArgValue(argValue);
            }
        }

        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }

}