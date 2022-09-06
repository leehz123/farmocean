package com.ezen.farmocean.mainPage.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Criteria {
	
	// ���� ������ ��ȣ
	private int pageNum;

	// ������ ǥ�� ����
	private int amount;
	
	// �˻� Ÿ��
	private String type;
	
	// �˻� Ű����
	private String keyword;
	
	// �Ǹ��� ���̵�
	private String[] member_id;
	
	// ī�װ� ���̵�
	private Integer cate_idx;
	
	// Criteria ������
	public Criteria (int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// Criteria �⺻ ������
	public Criteria() {
		this(1, 10);
	}
	
	// �˻� Ÿ�� ������ �迭 ��ȯ
	public String[] getTypeArr() {
		return type == null ? new String[] {}:type.split("");
	}
}
