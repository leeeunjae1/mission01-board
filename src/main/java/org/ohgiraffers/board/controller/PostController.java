package org.ohgiraffers.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 레이어드 아키텍쳐
 * 소프트웨어를 여러개의 계층으로 분리해서 설계하는 방법
 * 각 계층이 독립적으로 구성되서, 한 계층이 변경이 일어나도, 다른 계층에 영향을 주지 않는다.
 * 따라서 코드의 재사용성과 유지보수성을 높일 수 있다.
 **/

/** Controller / RestController
 *  Controller : 주로 화면 View를 반환하기 위해 사용된다.
 *  하지만 종종 Controller를 사용하면서도 데이터를 반환해야 할 때가 있는데, 이럴 때 사용하는 것이 @ResponseBody이다.
 *  RestController : Controller + @ResponseBody라고 생각하면 된다.
 *
 *  REST란?
 *  Representational AState Transfer의 약자
 *  자원을 이름으로 구분하여 자원의 상태를 주고 받는 것을 의미한다.
 *  REST는 기본적으로 웹의 기존 HTTP 프로토콜을 그대로 사용하기 때문에, 웹의 장점을 최대한 활용 할 수 있는 아키텍쳐 스타일이다.
 */

@RestController
// @RequestMapping : 특정 URL을 매핑하게 도와준다.
@RequestMapping("/api/v1/posts")
// @RequiredArgsConstructor : final 혹은 @NotNull 어노테이션으 붙은 필드에 대한 생성자를 자동으로 생성해준다.
@RequiredArgsConstructor
public class PostController {
}
