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
<label for="profile">프로필 사진</label>
<input id="profile" type="file" accept="image/*"/>
```

Label 태그의 값, "프로필 사진"을 클릭하면 input을 클릭한 것으로 적용된다.
input 태그의 id 값을 label 태그의 for 값에 맞추면 된다.

## CSS

html에 css를 포함하는 방법은 2가지이다.

1. `<head></head>` 안에 `<style></style>` 태그를 선언하고 해당 태그에 css를 작성한다.
2. `<head></head>` 안에 `<link />` 태그를 선언하고 css 파일을 불러온다.

### 1번 방법

```html

<head>
	<style>
		body {
			background-color: tomato;
		}
	</style>
</head>
```

### 2번 방법

```html

<head>
	<link rel="stylesheet" href="style.css">
</head>
```

일반적으로는 2번 방법이 추천된다.