# NomadCoder CoCoaTalk Clone Coding

- HTML 문법 복습겸 코코아톡을 만들어보자

## HEAD and Body

html은 아래 두 부분으로 구성된다.

- `<head></head>`
- `<body></body>`

```html
<!DOCTYPE html>
<html>
<head>
</head>
<body>
</body>
</html>
```

### head

외부적으로 보이지 않는 웹사이트의 환경을 설정한다.
이 안에 있는 브라우저가 이해하는 실존 동작하는 html 태그들은 보여지지 않는다.

### body

사용자가 볼 수 있는 content를 보여준다.
브라우저 화면 상에 보여질 내용들은 모두 여기에 있어야한다.

## Label
```html
<label for="profile"></label>
<input id="profile" type="file" accept="image/*"/>
```