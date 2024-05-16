package com.gdu.prj.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MyPageUtils {

  private int total;    // 전체 게시글 개수                     
  private int display;  // 한 페이지에 표시할 게시글 개수        
  private int page;     // 현재 페이지 번호                      
  private int begin;    // 한 페이지에 표시할 게시글의 시작 번호 
  private int end;      // 한 페이지에 표시할 게시글의 종료 번호 
  
  private int pagePerBlock = 10;    // 한 블록에 표시할 페이지 링크의 개수     
  private int totalPage;            // 전체 페이지 개수                        
  private int beginPage;            // 한 블록에 표시할 페이지 링크의 시작 번호 
  private int endPage;              // 한 블록에 표시할 페이지 링크의 종료 번호 
  
  public void setPaging(int total, int display, int page) {
    this.total = total;
    this.display = display;
    this.page = page;
    
    begin = (page - 1) * display + 1; // 만약 3page 뽑겠다 -> 2*40+1 =  41번부터 가져옴
    end   = begin + display - 1;
    
    totalPage = (int) Math.ceil((double)total / display);    //101/20 일 경우...이런 경우를 위한 처리
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1; //page 1~10까지는 1블록의 1 나와야함.
    endPage = Math.min(totalPage, beginPage + pagePerBlock - 1);  // end 에서 필요한 보정은 51페이지 까지만 있는데 51 52 53 54 이렇게 나오는 거 보정


    //BOARD_NO 의 경우 수정 삽입 삭제 등의 과정에서 BOARD_NO가 변하면서[(123)(2번삭제)(13)] 데이터개수가 차이가 나기 때문에 사용할 수 없음.
    //정렬 후 생성한 ROW_NUMBER 를 통해 계산한다. 
  }
  
  public String getPaging(String requestURI, String sort, int display) {
    
    StringBuilder builder = new StringBuilder();
    
    // <
    if(beginPage == 1) {
    builder.append("<div class=\"dont-click\">&lt;</div>");
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (beginPage - 1) + "&sort="+ sort +"&display="+ display +"\">&lt;</a></div>");
    }
    
    // 1 2 3 4 5 6 7 8 9 10
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {
        builder.append("<div><a class =\"current-page\" href=\"" + requestURI + "?page=" + p + "&sort=" + sort+ "&display=" + display + "\">"+ p +"</a></div>");
      } else {
        builder.append("<div><a href=\"" + requestURI + "?page=" + p + "&sort="+ sort + "&display=" + display + "\">" + p +"</a></div>");
      }
    }
    
    // >
    if(endPage == totalPage) {
    builder.append("<div class= \"dont-click\">&gt;</div>");
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "&display=" + display + "\">&gt;</a></div>");
    }
    
    
    return builder.toString();
  }
  
}
