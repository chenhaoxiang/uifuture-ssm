/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */

/**
 * @author chenhx
 * @version Test.java, v 0.1 2019-06-25 22:29 chenhx
 */
public class Test {
//    /**
//     * Implementation of {@code InvocationHandler.invoke}.
//     * <p>Callers will see exactly the exception thrown by the target,
//     * unless a hook method throws an exception.
//     */
//    @Override
//    @Nullable
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        MethodInvocation invocation;
//        Object oldProxy = null;
//        boolean setProxyContext = false;
//
//        TargetSource targetSource = this.advised.targetSource;
//        Object target = null;
//
//        try {
//            if (!this.equalsDefined && AopUtils.isEqualsMethod(method)) {
//                // 不代理 equals 方法，通过 JdkDynamicAopProxy 中定义的 equals 方法进行比较
//                return equals(args[0]);
//            }
//            else if (!this.hashCodeDefined && AopUtils.isHashCodeMethod(method)) {
//                // 不代理 hashCode 方法，调用 JdkDynamicAopProxy 中定义的 hashCode 方法
//                return hashCode();
//            }
//            else if (method.getDeclaringClass() == DecoratingProxy.class) {
//                // DecoratingProxy 接口中只有 getDecoratedClass 方法，通过下面的方法实现其逻辑
//                return AopProxyUtils.ultimateTargetClass(this.advised);
//            }
//            else if (!this.advised.opaque && method.getDeclaringClass().isInterface() &&
//                    method.getDeclaringClass().isAssignableFrom(Advised.class)) {
//                // this.advised.opaque 为 false 才可能代理 Advised 接口
//                // 如果 method 是在 Advised 中声明的，则直接把 method 转移到 advised 对象上调用。
//                // 这样就可以通过代理对象直接操作代理的配置，比如新增 Advisor。
//                //简单的理解就是:Spring AOP不会增强直接实现Advised接口的目标对象，再重复一次，也就是说如果目标对象实现的Advised接口，则不会对其应用切面进行方法的增强。
//                return AopUtils.invokeJoinpointUsingReflection(this.advised, method, args);
//            }
//            //方法的返回值
//            Object retVal;
//
//            //是否暴露代理对象，默认false可配置为true，如果暴露就意味着允许在线程内共享代理对象，注意这是在线程内，也就是说同一线程的任意地方都能通过AopContext获取该代理对象
//            if (this.advised.exposeProxy) {
//                // 如果需要在拦截器暴露 proxy 对象，则把 proxy 对象添加到 ThreadLocal 中
//                oldProxy = AopContext.setCurrentProxy(proxy);
//                setProxyContext = true;
//            }
//
//            //通过目标源获取目标对象
//            target = targetSource.getTarget();
//            //获取目标对象Class对象
//            Class<?> targetClass = (target != null ? target.getClass() : null);
//
//            // 获取此方法的拦截器链
//            List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
//
//
//            if (chain.isEmpty()) {
//                // 如果拦截器集合为空，说明当前 method 不需要被增强，则通过反射直接调用目标对象上的方法
//                Object[] argsToUse = AopProxyUtils.adaptArgumentsIfNecessary(method, args);
//                // 没有拦截器链则直接执行目标方法
//                retVal = AopUtils.invokeJoinpointUsingReflection(target, method, argsToUse);
//            }
//            else {
//                // 创建 ReflectiveMethodInvocation，用来管理方法拦截器责任链.通过ReflectiveMethodInvocation.proceed调用拦截器中的方法和目标对象方法
//                invocation = new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain);
//                //ReflectiveMethodInvocation对象完成对AOP功能实现的封装，并获取到返回值
//                retVal = invocation.proceed();
//            }
//
//            // 返回 this，需要替换为 proxy 对象
//            Class<?> returnType = method.getReturnType();
//            if (retVal != null && retVal == target &&
//                    returnType != Object.class && returnType.isInstance(proxy) &&
//                    !RawTargetAccess.class.isAssignableFrom(method.getDeclaringClass())) {
//                // Special case: it returned "this" and the return type of the method
//                // is type-compatible. Note that we can't help if the target sets
//                // a reference to itself in another returned object.
//                retVal = proxy;
//            }
//            else if (retVal == null && returnType != Void.TYPE && returnType.isPrimitive()) {
//                throw new AopInvocationException(
//                        "Null return value from advice does not match primitive return type for: " + method);
//            }
//            return retVal;
//        }
//        finally {
//            if (target != null && !targetSource.isStatic()) {
//                // Must have come from TargetSource.
//                targetSource.releaseTarget(target);
//            }
//            if (setProxyContext) {
//                // 把第一次调用 setCurrentProxy 返回的对象，重新设置到 ThreadLocal 中
//                AopContext.setCurrentProxy(oldProxy);
//            }
//        }
//    }
//
//
///**
// * 该方法的主要逻辑在于通过拦截器链，遍历其中的拦截器，再通过匹配判断是否适用，如果适用则取出拦截器中的通知器并通过通知器的invoke方法进行调用，如果不适用则继续递归调用
// * @return
// * @throws Throwable
// */
//@Override
//@Nullable
//public Object proceed() throws Throwable {
//    //	We start with an index of -1 and increment early.
//    if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
//        //调用完成所有拦截器链中的拦截器增强方法，则直接调用模板对象的方法并且退出.invokeJoinpoint方法就是直接调用了AopUtils.invokeJoinpointUsingReflection方法
//        return invokeJoinpoint();
//    }
//
//    //从拦截器链中获取拦截器
//    Object interceptorOrInterceptionAdvice =
//            this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
//    if (interceptorOrInterceptionAdvice instanceof InterceptorAndDynamicMethodMatcher) {
////            在此处进行动态匹配，静态部分的匹配将已被评估并找到匹配项
//        InterceptorAndDynamicMethodMatcher dm =
//                (InterceptorAndDynamicMethodMatcher) interceptorOrInterceptionAdvice;
//        if (dm.methodMatcher.matches(this.method, this.targetClass, this.arguments)) {
//            //如果和定义的切点匹配，则这个通知便会执行
//            return dm.interceptor.invoke(this);
//        }
//        else {
//            //没有使用的拦截器，进行递归，继续将拦截器，匹配、判断和调用
//            return proceed();
//        }
//    }
//    else {
//        //判断出这个拦截器是MethodInterceptor，直接进行调用
//        return ((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);
//    }
//}
}
