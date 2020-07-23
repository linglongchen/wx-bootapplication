package com.modules.common.generator;

public class MainGenrator {
	public static void main(String[] args) throws Exception {
		CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
		codeGenerateUtils.generate();
		System.out.println("代码生成成功!");
	}
}
