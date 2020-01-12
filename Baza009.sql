PGDMP                          x            Projekt    12.1    12.1 B    ~           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17068    Projekt    DATABASE     �   CREATE DATABASE "Projekt" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Polish_Poland.1250' LC_CTYPE = 'Polish_Poland.1250';
    DROP DATABASE "Projekt";
                postgres    false            �            1259    17069    dzien    TABLE     P   CREATE TABLE public.dzien (
    "Id_dnia" integer NOT NULL,
    "Dzien" text
);
    DROP TABLE public.dzien;
       public         heap    postgres    false            �            1259    17075    events    TABLE     s   CREATE TABLE public.events (
    id_event integer NOT NULL,
    event text,
    data date,
    id_klasy integer
);
    DROP TABLE public.events;
       public         heap    postgres    false            �            1259    17081    godzina    TABLE     �   CREATE TABLE public.godzina (
    "Id_godzina" integer NOT NULL,
    "Id_dnia" integer NOT NULL,
    "Ktora_godzina" text,
    id_godziny integer NOT NULL
);
    DROP TABLE public.godzina;
       public         heap    postgres    false            �            1259    17087    klasa    TABLE     S   CREATE TABLE public.klasa (
    id_klasy integer NOT NULL,
    nazwa_klasy text
);
    DROP TABLE public.klasa;
       public         heap    postgres    false            �           0    0    TABLE klasa    COMMENT     V   COMMENT ON TABLE public.klasa IS 'zawierająca podstawowe informacje na temat klasy';
          public          postgres    false    205            �            1259    17093    lekcja    TABLE     �   CREATE TABLE public.lekcja (
    id_lekcji integer NOT NULL,
    id_godziny integer,
    id_nauczyciela integer,
    id_przedmiotu integer,
    sala integer
);
    DROP TABLE public.lekcja;
       public         heap    postgres    false            �            1259    17096    nauczyciele    TABLE     �   CREATE TABLE public.nauczyciele (
    id_nauczyciela integer NOT NULL,
    "imię" text,
    nazwisko text,
    email text,
    "hasło" text,
    id_przedmiotu integer
);
    DROP TABLE public.nauczyciele;
       public         heap    postgres    false            �            1259    17102    oceny    TABLE     �   CREATE TABLE public.oceny (
    id_przedmiotu integer,
    data date,
    id_oceny integer NOT NULL,
    rodzaj_oceny text,
    ocena integer,
    id_ucznia integer,
    semestr integer
);
    DROP TABLE public.oceny;
       public         heap    postgres    false            �            1259    17108    plan    TABLE     W   CREATE TABLE public.plan (
    "Id_klasy" integer NOT NULL,
    "Id_lekcji" integer
);
    DROP TABLE public.plan;
       public         heap    postgres    false            �            1259    17111 
   przedmioty    TABLE     b   CREATE TABLE public.przedmioty (
    id_przedmiotu integer NOT NULL,
    nazwa_przedmiotu text
);
    DROP TABLE public.przedmioty;
       public         heap    postgres    false            �            1259    17117    relacje    TABLE     a   CREATE TABLE public.relacje (
    id_ucznia integer NOT NULL,
    id_rodzica integer NOT NULL
);
    DROP TABLE public.relacje;
       public         heap    postgres    false            �            1259    17120    rodzice    TABLE     �   CREATE TABLE public.rodzice (
    id_rodzica integer NOT NULL,
    "imię" text,
    nazwisko text,
    email text,
    "hasło" text
);
    DROP TABLE public.rodzice;
       public         heap    postgres    false            �            1259    17126 	   uczniowie    TABLE       CREATE TABLE public.uczniowie (
    "miejsce zamieszkania" text,
    "data urodzenia" date,
    "dojeżdża" boolean,
    id_ucznia integer NOT NULL,
    email text NOT NULL,
    id_klasy integer,
    "miejsce urodzenia" text,
    "imię" text,
    nazwisko text,
    "hasło" text
);
    DROP TABLE public.uczniowie;
       public         heap    postgres    false            �           0    0    TABLE uczniowie    COMMENT     J   COMMENT ON TABLE public.uczniowie IS 'do przechowywania danych o uczniu';
          public          postgres    false    213            �            1259    17132    uczy    TABLE     a   CREATE TABLE public.uczy (
    id_nauczyciela integer NOT NULL,
    id_klasy integer NOT NULL
);
    DROP TABLE public.uczy;
       public         heap    postgres    false            �            1259    17135    uwagi    TABLE     �   CREATE TABLE public.uwagi (
    data date,
    id_nauczyciela integer,
    id_ucznia integer,
    id_uwagi integer NOT NULL,
    uwaga text
);
    DROP TABLE public.uwagi;
       public         heap    postgres    false            �            1259    17141 
   wiadomosci    TABLE     �   CREATE TABLE public.wiadomosci (
    id_nauczyciela integer,
    id_rodzica integer,
    wiadomosc text,
    czas time without time zone,
    data date,
    id_wiadomosci integer NOT NULL,
    autor text
);
    DROP TABLE public.wiadomosci;
       public         heap    postgres    false            m          0    17069    dzien 
   TABLE DATA           3   COPY public.dzien ("Id_dnia", "Dzien") FROM stdin;
    public          postgres    false    202   0K       n          0    17075    events 
   TABLE DATA           A   COPY public.events (id_event, event, data, id_klasy) FROM stdin;
    public          postgres    false    203   MK       o          0    17081    godzina 
   TABLE DATA           W   COPY public.godzina ("Id_godzina", "Id_dnia", "Ktora_godzina", id_godziny) FROM stdin;
    public          postgres    false    204   ^L       p          0    17087    klasa 
   TABLE DATA           6   COPY public.klasa (id_klasy, nazwa_klasy) FROM stdin;
    public          postgres    false    205   {L       q          0    17093    lekcja 
   TABLE DATA           \   COPY public.lekcja (id_lekcji, id_godziny, id_nauczyciela, id_przedmiotu, sala) FROM stdin;
    public          postgres    false    206   �L       r          0    17096    nauczyciele 
   TABLE DATA           h   COPY public.nauczyciele (id_nauczyciela, "imię", nazwisko, email, "hasło", id_przedmiotu) FROM stdin;
    public          postgres    false    207   �L       s          0    17102    oceny 
   TABLE DATA           g   COPY public.oceny (id_przedmiotu, data, id_oceny, rodzaj_oceny, ocena, id_ucznia, semestr) FROM stdin;
    public          postgres    false    208   N       t          0    17108    plan 
   TABLE DATA           7   COPY public.plan ("Id_klasy", "Id_lekcji") FROM stdin;
    public          postgres    false    209   zO       u          0    17111 
   przedmioty 
   TABLE DATA           E   COPY public.przedmioty (id_przedmiotu, nazwa_przedmiotu) FROM stdin;
    public          postgres    false    210   �O       v          0    17117    relacje 
   TABLE DATA           8   COPY public.relacje (id_ucznia, id_rodzica) FROM stdin;
    public          postgres    false    211   P       w          0    17120    rodzice 
   TABLE DATA           Q   COPY public.rodzice (id_rodzica, "imię", nazwisko, email, "hasło") FROM stdin;
    public          postgres    false    212   EP       x          0    17126 	   uczniowie 
   TABLE DATA           �   COPY public.uczniowie ("miejsce zamieszkania", "data urodzenia", "dojeżdża", id_ucznia, email, id_klasy, "miejsce urodzenia", "imię", nazwisko, "hasło") FROM stdin;
    public          postgres    false    213   MQ       y          0    17132    uczy 
   TABLE DATA           8   COPY public.uczy (id_nauczyciela, id_klasy) FROM stdin;
    public          postgres    false    214   -S       z          0    17135    uwagi 
   TABLE DATA           Q   COPY public.uwagi (data, id_nauczyciela, id_ucznia, id_uwagi, uwaga) FROM stdin;
    public          postgres    false    215   YS       {          0    17141 
   wiadomosci 
   TABLE DATA           m   COPY public.wiadomosci (id_nauczyciela, id_rodzica, wiadomosc, czas, data, id_wiadomosci, autor) FROM stdin;
    public          postgres    false    216   �S       �
           2606    17148    dzien Dzien_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.dzien
    ADD CONSTRAINT "Dzien_pkey" PRIMARY KEY ("Id_dnia");
 <   ALTER TABLE ONLY public.dzien DROP CONSTRAINT "Dzien_pkey";
       public            postgres    false    202            �
           2606    17150    klasa Klasa_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.klasa
    ADD CONSTRAINT "Klasa_pkey" PRIMARY KEY (id_klasy) INCLUDE (id_klasy);
 <   ALTER TABLE ONLY public.klasa DROP CONSTRAINT "Klasa_pkey";
       public            postgres    false    205    205            �
           2606    17152    nauczyciele PK_Id_nauczyciela 
   CONSTRAINT     i   ALTER TABLE ONLY public.nauczyciele
    ADD CONSTRAINT "PK_Id_nauczyciela" PRIMARY KEY (id_nauczyciela);
 I   ALTER TABLE ONLY public.nauczyciele DROP CONSTRAINT "PK_Id_nauczyciela";
       public            postgres    false    207            �
           2606    17154    plan Plan_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.plan
    ADD CONSTRAINT "Plan_pkey" PRIMARY KEY ("Id_klasy");
 :   ALTER TABLE ONLY public.plan DROP CONSTRAINT "Plan_pkey";
       public            postgres    false    209            �
           2606    17156    uczniowie U_id_ucznia 
   CONSTRAINT     W   ALTER TABLE ONLY public.uczniowie
    ADD CONSTRAINT "U_id_ucznia" UNIQUE (id_ucznia);
 A   ALTER TABLE ONLY public.uczniowie DROP CONSTRAINT "U_id_ucznia";
       public            postgres    false    213            �
           2606    17158    uczniowie Uczniowie_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY public.uczniowie
    ADD CONSTRAINT "Uczniowie_pkey" PRIMARY KEY (id_ucznia) INCLUDE (id_ucznia);
 D   ALTER TABLE ONLY public.uczniowie DROP CONSTRAINT "Uczniowie_pkey";
       public            postgres    false    213    213            �
           2606    17160    nauczyciele Uid_nauczyciela 
   CONSTRAINT     b   ALTER TABLE ONLY public.nauczyciele
    ADD CONSTRAINT "Uid_nauczyciela" UNIQUE (id_nauczyciela);
 G   ALTER TABLE ONLY public.nauczyciele DROP CONSTRAINT "Uid_nauczyciela";
       public            postgres    false    207            �
           2606    17162    events events_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id_event);
 <   ALTER TABLE ONLY public.events DROP CONSTRAINT events_pkey;
       public            postgres    false    203            �
           2606    17164    relacje fk_id_ucznia_id_rodzica 
   CONSTRAINT     p   ALTER TABLE ONLY public.relacje
    ADD CONSTRAINT fk_id_ucznia_id_rodzica PRIMARY KEY (id_ucznia, id_rodzica);
 I   ALTER TABLE ONLY public.relacje DROP CONSTRAINT fk_id_ucznia_id_rodzica;
       public            postgres    false    211    211            �
           2606    17166    lekcja lekcja_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.lekcja
    ADD CONSTRAINT lekcja_pkey PRIMARY KEY (id_lekcji);
 <   ALTER TABLE ONLY public.lekcja DROP CONSTRAINT lekcja_pkey;
       public            postgres    false    206            �
           2606    17168    oceny oceny_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.oceny
    ADD CONSTRAINT oceny_pkey PRIMARY KEY (id_oceny);
 :   ALTER TABLE ONLY public.oceny DROP CONSTRAINT oceny_pkey;
       public            postgres    false    208            �
           2606    17170    godzina pk_id_godzina 
   CONSTRAINT     ]   ALTER TABLE ONLY public.godzina
    ADD CONSTRAINT pk_id_godzina PRIMARY KEY ("Id_godzina");
 ?   ALTER TABLE ONLY public.godzina DROP CONSTRAINT pk_id_godzina;
       public            postgres    false    204            �
           2606    17172    przedmioty pk_id_przedmiotu 
   CONSTRAINT     d   ALTER TABLE ONLY public.przedmioty
    ADD CONSTRAINT pk_id_przedmiotu PRIMARY KEY (id_przedmiotu);
 E   ALTER TABLE ONLY public.przedmioty DROP CONSTRAINT pk_id_przedmiotu;
       public            postgres    false    210            �
           2606    17174    rodzice pk_id_rodzica 
   CONSTRAINT     [   ALTER TABLE ONLY public.rodzice
    ADD CONSTRAINT pk_id_rodzica PRIMARY KEY (id_rodzica);
 ?   ALTER TABLE ONLY public.rodzice DROP CONSTRAINT pk_id_rodzica;
       public            postgres    false    212            �
           2606    17176    uczy uczy_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.uczy
    ADD CONSTRAINT uczy_pkey PRIMARY KEY (id_nauczyciela, id_klasy);
 8   ALTER TABLE ONLY public.uczy DROP CONSTRAINT uczy_pkey;
       public            postgres    false    214    214            �
           2606    17178    uwagi uwagi_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.uwagi
    ADD CONSTRAINT uwagi_pkey PRIMARY KEY (id_uwagi);
 :   ALTER TABLE ONLY public.uwagi DROP CONSTRAINT uwagi_pkey;
       public            postgres    false    215            �
           2606    17180    wiadomosci wiadomosci_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.wiadomosci
    ADD CONSTRAINT wiadomosci_pkey PRIMARY KEY (id_wiadomosci);
 D   ALTER TABLE ONLY public.wiadomosci DROP CONSTRAINT wiadomosci_pkey;
       public            postgres    false    216            �
           1259    17181    fki_fk_id_nauczyciela    INDEX     Q   CREATE INDEX fki_fk_id_nauczyciela ON public.uwagi USING btree (id_nauczyciela);
 )   DROP INDEX public.fki_fk_id_nauczyciela;
       public            postgres    false    215            �
           2606    17182    godzina Godzina_Id_dnia_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.godzina
    ADD CONSTRAINT "Godzina_Id_dnia_fkey" FOREIGN KEY ("Id_dnia") REFERENCES public.dzien("Id_dnia") NOT VALID;
 H   ALTER TABLE ONLY public.godzina DROP CONSTRAINT "Godzina_Id_dnia_fkey";
       public          postgres    false    202    2753    204            �
           2606    17187    plan fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.plan
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY ("Id_klasy") REFERENCES public.klasa(id_klasy) NOT VALID;
 :   ALTER TABLE ONLY public.plan DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    2759    209    205            �
           2606    17192    uczy fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczy
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY (id_klasy) REFERENCES public.klasa(id_klasy) NOT VALID;
 :   ALTER TABLE ONLY public.uczy DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    214    205    2759            �
           2606    17197    uczniowie fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczniowie
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY (id_klasy) REFERENCES public.klasa(id_klasy) NOT VALID;
 ?   ALTER TABLE ONLY public.uczniowie DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    213    205    2759            �
           2606    17202    events fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.events
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY (id_klasy) REFERENCES public.klasa(id_klasy) NOT VALID;
 <   ALTER TABLE ONLY public.events DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    203    2759    205            �
           2606    17207    uczy fk_id_nauczyciela    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczy
    ADD CONSTRAINT fk_id_nauczyciela FOREIGN KEY (id_nauczyciela) REFERENCES public.nauczyciele(id_nauczyciela);
 @   ALTER TABLE ONLY public.uczy DROP CONSTRAINT fk_id_nauczyciela;
       public          postgres    false    2763    214    207            �
           2606    17212    uwagi fk_id_nauczyciela    FK CONSTRAINT     �   ALTER TABLE ONLY public.uwagi
    ADD CONSTRAINT fk_id_nauczyciela FOREIGN KEY (id_nauczyciela) REFERENCES public.nauczyciele(id_nauczyciela) NOT VALID;
 A   ALTER TABLE ONLY public.uwagi DROP CONSTRAINT fk_id_nauczyciela;
       public          postgres    false    2763    207    215            �
           2606    17217    oceny fk_id_przedmiotu    FK CONSTRAINT     �   ALTER TABLE ONLY public.oceny
    ADD CONSTRAINT fk_id_przedmiotu FOREIGN KEY (id_przedmiotu) REFERENCES public.przedmioty(id_przedmiotu) NOT VALID;
 @   ALTER TABLE ONLY public.oceny DROP CONSTRAINT fk_id_przedmiotu;
       public          postgres    false    2771    210    208            �
           2606    17222    relacje fk_id_rodzica    FK CONSTRAINT     �   ALTER TABLE ONLY public.relacje
    ADD CONSTRAINT fk_id_rodzica FOREIGN KEY (id_rodzica) REFERENCES public.rodzice(id_rodzica) NOT VALID;
 ?   ALTER TABLE ONLY public.relacje DROP CONSTRAINT fk_id_rodzica;
       public          postgres    false    2775    212    211            �
           2606    17227    relacje fk_id_ucznia    FK CONSTRAINT     �   ALTER TABLE ONLY public.relacje
    ADD CONSTRAINT fk_id_ucznia FOREIGN KEY (id_ucznia) REFERENCES public.uczniowie(id_ucznia);
 >   ALTER TABLE ONLY public.relacje DROP CONSTRAINT fk_id_ucznia;
       public          postgres    false    2777    213    211            �
           2606    17232    oceny fk_id_ucznia    FK CONSTRAINT     �   ALTER TABLE ONLY public.oceny
    ADD CONSTRAINT fk_id_ucznia FOREIGN KEY (id_ucznia) REFERENCES public.uczniowie(id_ucznia) NOT VALID;
 <   ALTER TABLE ONLY public.oceny DROP CONSTRAINT fk_id_ucznia;
       public          postgres    false    213    208    2777            �
           2606    17237    uwagi fk_id_ucznia    FK CONSTRAINT     �   ALTER TABLE ONLY public.uwagi
    ADD CONSTRAINT fk_id_ucznia FOREIGN KEY (id_ucznia) REFERENCES public.uczniowie(id_ucznia) NOT VALID;
 <   ALTER TABLE ONLY public.uwagi DROP CONSTRAINT fk_id_ucznia;
       public          postgres    false    2777    215    213            m      x������ � �      n     x���Mj�0���)f�B4R���v�M��`��"[
�!��lz��c���Ձj(]�d!�F�7<	Ŧ�����v��!: �b˪uf����"/}'��*Q:A-�Կ��p�v������u}��u<�}a�mEyM�v�\1�ܜ����L#�bCM��
��竡��c}�t9A�y�t2ֳe ����j�;������]ƺ�u�O���mX�Jl�	
�@�M�F����71sH/�|V�a?0��O)�7���0      o      x������ � �      p      x�3�4q�2�4q�2�4u����� |�      q      x������ � �      r   8  x�eQMK�0=O�� �n��͊�� � ���	�&m��k ��K^�I���օ,^���<�7ꝳn��1�v��0-�67�=j�[ׯ�(6e�PQ�Y�6:�����H��eԪ��V�v>`p��p�����bȈ({=Т
�����Մ��a>�0e���@w�S�x��Z= 4������qs�2���]BݣT)�&�Mǒ�N��WB���+x!�c2	[gܪZC�#u��<�)��,���|YH����l�!<@��y����5�����ܤ֞,���I�6t=��`;y�Y���W���`��1����      s   ^  x��T9N1�=������o ��XРiFF�����UFB�dյ�:��^��H����t~߾>�-���ɠ�\��9=���9��Gr�BB	��u)�k�n���xz\�?�| �#��A��H$��s"H���B�E��/-�s`!i*s���Im�X�n�uJ+��L��"�h��	 c�Ų�%%�;w�D�^�լ��GC
 �*��}�蔯i��d5�<q�д�oz��,V�ՙ�Չ7��9,�ҥ�`@����k���r�ƞZ�%fx�t���Vҍݳ)B� �K!��2�������߁m�1!c�A�g�#l�1�'�O�*Ɛi��x=�AW^�      t      x������ � �      u   ^   x�3��M,I�M,��N���2���,.�/�qL9�2�s��3��8�2��J��9=��� ��9����+��ggr����y险 !�11z\\\ )�"�      v   0   x��I   İw+�a��$����`8Y.����x���jB�+��t�      w   �   x�m��N1E��<xt@J�H��f����^Y�e�_�t�����(Ls�tf��V���'��裿|�$Ԥ�l:�/�)���L���GhR��
�a��1�gl�m;��55Ϲ;�n{E���">���qo#I��5v��9�U�͑ϗe��3,{����H�F��%!
�c�s��Y�U�v�����/�68lm�4"��XjM��)�t�f4����k������?�!m,���y�4M��c�      x   �  x�m�On�0�׏S��0$�5�VQ&hR�R����kcw�������n+�5�@&�4Y�(���{�������0b?���'a��P��9
5IMN�$��Eʇ3ؖ&ݻg�r�%V~����l��4�B�:�� J�`�%�3j7����q[�	��W*�;��P?}�5��J)dapc��Ҽ��dşP���#u�:�~�,�:�@���$�E��?��.,�Εb��Ҍ�f$m��qUUY�$����z�$%3QeW$EFs�E����X�vc��X"܋�Ϙ.���b֎H64+C+�t���rF=�ظ�W�tvڴ�[�����b<e�c�7�II�Y�h���0'�՘m���Eqw����R�����Q�/���z �@���_��4�!1A�ٖ��_����!9^ մ�8���f�������0A�mn`�aN��~5��]�X����?�0'      y      x�3�4�2�4�2cc 6����� '��      z   �   x�3202�50�5��4�4���Û����L".#�����9�!Pސө(1[��(1�R!%?7�<5U�!�A5F@�� �0�ʯ�M,�LT�KT�I�N�ʄ�14 �0�4�g��9�Gf��1�����a���(�J-��NL�B6$F��� Y�V-      {   �   x�M�1
�0 ������O�]��*�..�fH��4"��%���K�|x$l���^�M��x)S�!q�mh@�jM�V�HQE��5(8p0w&���MN.�0�}ⱼ��88'�9f��߲�5��"a3���nc���M�B| �+H     