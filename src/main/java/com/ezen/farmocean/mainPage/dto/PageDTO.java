package com.ezen.farmocean.mainPage.dto;

import lombok.Data;

@Data
public class PageDTO {
	
	// ������ ���� ��ȣ 
    private int pageStart;
    
    // ������ �� ��ȣ
    private int pageEnd;
    
    // ����, ���� ��ư ���� ����
    private boolean next, prev;
    
    // �� ��ü ����
    private int total;
    
    // ���������� ��ȣ(pageNum), �� ǥ�� ��(amount), �˻� Ű����(keyword), �˻� ����(type)
    private Criteria cri;
    
    // ������(Ŭ���� ȣ�� �� �� ���� �� �ʱ�ȭ) 
    public PageDTO(Criteria cri, int total) {
        
        // cri, total �ʱ�ȭ 
        this.cri = cri;
        this.total = total;
        
        // ������ �� ��ȣ 
        this.pageEnd = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
        
        // ������ ���� ��ȣ
        this.pageStart = this.pageEnd - 9;
        
        // ��ü ������ ������ ��ȣ
        int realEnd = (int)(Math.ceil(total*1.0/cri.getAmount()));
        
        // ������ �� ��ȣ ��ȿ�� üũ
        if(realEnd < pageEnd) {
            this.pageEnd = realEnd;
        }
        
        // ���� ��ư �� �ʱ�ȭ
        this.prev = this.pageStart > 1;
        
        // ���� ��ư �� �ʱ�ȭ
        this.next = this.pageEnd < realEnd;
        
    }

}
