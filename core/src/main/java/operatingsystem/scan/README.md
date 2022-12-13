### Scan 알고리즘

- [ ] 알고리즘은 초기 시작할떄 방향을 정한다 (좌, 우)
- [X] 1 ~ 1000ms 사이의 시간을 임의로 정할 수 있어야 한다.
- [X] 시작 헤드위치와 끝 헤드위치 사이의 임의의 값을 정할 수 있어야 한다.
- [ ] 방향을 정하고 헤드가 이동을 시작할때 동시에 임의의 시간 및 값을 정할 수 있다.
- [ ] 임의로 생성된 값은 PQ에 들어가야 한다.
  - [X] PQ는 Integer값을 담는다.
  - [X] PQ는 reverse와 정상인 PQ가 존재한다.
    - [X] reverse PQ는 left PQ이다.
    - [X] 그냥 pQ는 right PQ이다.
  - [ ] 임의로 생성된 값은 현재 head의 위치와 비교한다.
    - [ ] 현재 head의 위치보다 크다면 right PQ에 삽입한다.
    - [ ] 현재 head의 위치보다 작다면 left PQ에 삽입한다.