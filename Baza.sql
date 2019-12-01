PGDMP         ;                w            Projekt    12.1    12.1 ;    h           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            i           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            j           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            k           1262    16567    Projekt    DATABASE     �   CREATE DATABASE "Projekt" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Polish_Poland.1250' LC_CTYPE = 'Polish_Poland.1250';
    DROP DATABASE "Projekt";
                postgres    false            �            1259    16568    dzien    TABLE     P   CREATE TABLE public.dzien (
    "Id_dnia" integer NOT NULL,
    "Dzien" text
);
    DROP TABLE public.dzien;
       public         heap    postgres    false            �            1259    16574    godzina    TABLE     }   CREATE TABLE public.godzina (
    "Id_godzina" integer NOT NULL,
    "Id_dnia" integer NOT NULL,
    "Ktora_godzina" text
);
    DROP TABLE public.godzina;
       public         heap    postgres    false            �            1259    16580    klasa    TABLE     u   CREATE TABLE public.klasa (
    "Id_klasy" integer NOT NULL,
    "Id_nauczyciela" integer,
    "Nazwa_klasy" text
);
    DROP TABLE public.klasa;
       public         heap    postgres    false            l           0    0    TABLE klasa    COMMENT     V   COMMENT ON TABLE public.klasa IS 'zawierająca podstawowe informacje na temat klasy';
          public          postgres    false    204            �            1259    16586    lekcje    TABLE     �   CREATE TABLE public.lekcje (
    "Id_lekcji" integer NOT NULL,
    "Id_godziny" integer NOT NULL,
    "Id_przedmiotu" integer NOT NULL,
    "Id_nauczyciela" integer NOT NULL,
    "Sala" integer
);
    DROP TABLE public.lekcje;
       public         heap    postgres    false            �            1259    16589    nauczyciele    TABLE     �   CREATE TABLE public.nauczyciele (
    id_nauczyciela integer NOT NULL,
    "imię" text,
    nazwisko text,
    email text,
    "hasło" text
);
    DROP TABLE public.nauczyciele;
       public         heap    postgres    false            �            1259    16595    oceny    TABLE     �   CREATE TABLE public.oceny (
    id_ucznia integer NOT NULL,
    id_przedmiotu integer,
    data date,
    id_oceny integer NOT NULL,
    rodzaj_oceny text,
    ocena integer
);
    DROP TABLE public.oceny;
       public         heap    postgres    false            �            1259    16601    plan    TABLE     W   CREATE TABLE public.plan (
    "Id_klasy" integer NOT NULL,
    "Id_lekcji" integer
);
    DROP TABLE public.plan;
       public         heap    postgres    false            �            1259    16604 
   przedmioty    TABLE     b   CREATE TABLE public.przedmioty (
    id_przedmiotu integer NOT NULL,
    nazwa_przedmiotu text
);
    DROP TABLE public.przedmioty;
       public         heap    postgres    false            �            1259    16610    relacje    TABLE     a   CREATE TABLE public.relacje (
    id_ucznia integer NOT NULL,
    id_rodzica integer NOT NULL
);
    DROP TABLE public.relacje;
       public         heap    postgres    false            �            1259    16613    rodzice    TABLE     �   CREATE TABLE public.rodzice (
    id_rodzica integer NOT NULL,
    "imię" text,
    nazwisko text,
    email text,
    "hasło" text,
    haslo character varying(255)
);
    DROP TABLE public.rodzice;
       public         heap    postgres    false            �            1259    16619 	   uczniowie    TABLE       CREATE TABLE public.uczniowie (
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
       public         heap    postgres    false            m           0    0    TABLE uczniowie    COMMENT     J   COMMENT ON TABLE public.uczniowie IS 'do przechowywania danych o uczniu';
          public          postgres    false    212            �            1259    16625    uczy    TABLE     �   CREATE TABLE public.uczy (
    "Id_nauczyciela" integer,
    "Id_przedmiotu" integer,
    "Id_klasy" integer,
    "Ile_godzin" integer
);
    DROP TABLE public.uczy;
       public         heap    postgres    false            Z          0    16568    dzien 
   TABLE DATA           3   COPY public.dzien ("Id_dnia", "Dzien") FROM stdin;
    public          postgres    false    202   �E       [          0    16574    godzina 
   TABLE DATA           K   COPY public.godzina ("Id_godzina", "Id_dnia", "Ktora_godzina") FROM stdin;
    public          postgres    false    203   �E       \          0    16580    klasa 
   TABLE DATA           L   COPY public.klasa ("Id_klasy", "Id_nauczyciela", "Nazwa_klasy") FROM stdin;
    public          postgres    false    204   �E       ]          0    16586    lekcje 
   TABLE DATA           f   COPY public.lekcje ("Id_lekcji", "Id_godziny", "Id_przedmiotu", "Id_nauczyciela", "Sala") FROM stdin;
    public          postgres    false    205   F       ^          0    16589    nauczyciele 
   TABLE DATA           Y   COPY public.nauczyciele (id_nauczyciela, "imię", nazwisko, email, "hasło") FROM stdin;
    public          postgres    false    206   !F       _          0    16595    oceny 
   TABLE DATA           ^   COPY public.oceny (id_ucznia, id_przedmiotu, data, id_oceny, rodzaj_oceny, ocena) FROM stdin;
    public          postgres    false    207   �F       `          0    16601    plan 
   TABLE DATA           7   COPY public.plan ("Id_klasy", "Id_lekcji") FROM stdin;
    public          postgres    false    208   G       a          0    16604 
   przedmioty 
   TABLE DATA           E   COPY public.przedmioty (id_przedmiotu, nazwa_przedmiotu) FROM stdin;
    public          postgres    false    209   *G       b          0    16610    relacje 
   TABLE DATA           8   COPY public.relacje (id_ucznia, id_rodzica) FROM stdin;
    public          postgres    false    210   �G       c          0    16613    rodzice 
   TABLE DATA           X   COPY public.rodzice (id_rodzica, "imię", nazwisko, email, "hasło", haslo) FROM stdin;
    public          postgres    false    211   �G       d          0    16619 	   uczniowie 
   TABLE DATA           �   COPY public.uczniowie ("miejsce zamieszkania", "data urodzenia", "dojeżdża", id_ucznia, email, id_klasy, "miejsce urodzenia", "imię", nazwisko, "hasło") FROM stdin;
    public          postgres    false    212   �G       e          0    16625    uczy 
   TABLE DATA           [   COPY public.uczy ("Id_nauczyciela", "Id_przedmiotu", "Id_klasy", "Ile_godzin") FROM stdin;
    public          postgres    false    213   �H       �
           2606    16629    dzien Dzien_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.dzien
    ADD CONSTRAINT "Dzien_pkey" PRIMARY KEY ("Id_dnia");
 <   ALTER TABLE ONLY public.dzien DROP CONSTRAINT "Dzien_pkey";
       public            postgres    false    202            �
           2606    16631    lekcje Id_lekcji 
   CONSTRAINT     Y   ALTER TABLE ONLY public.lekcje
    ADD CONSTRAINT "Id_lekcji" PRIMARY KEY ("Id_lekcji");
 <   ALTER TABLE ONLY public.lekcje DROP CONSTRAINT "Id_lekcji";
       public            postgres    false    205            �
           2606    16633    klasa Klasa_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.klasa
    ADD CONSTRAINT "Klasa_pkey" PRIMARY KEY ("Id_klasy") INCLUDE ("Id_klasy");
 <   ALTER TABLE ONLY public.klasa DROP CONSTRAINT "Klasa_pkey";
       public            postgres    false    204    204            �
           2606    16635    nauczyciele PK_Id_nauczyciela 
   CONSTRAINT     i   ALTER TABLE ONLY public.nauczyciele
    ADD CONSTRAINT "PK_Id_nauczyciela" PRIMARY KEY (id_nauczyciela);
 I   ALTER TABLE ONLY public.nauczyciele DROP CONSTRAINT "PK_Id_nauczyciela";
       public            postgres    false    206            �
           2606    16637    plan Plan_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.plan
    ADD CONSTRAINT "Plan_pkey" PRIMARY KEY ("Id_klasy");
 :   ALTER TABLE ONLY public.plan DROP CONSTRAINT "Plan_pkey";
       public            postgres    false    208            �
           2606    16639    klasa U_id_nauczyciela 
   CONSTRAINT     z   ALTER TABLE ONLY public.klasa
    ADD CONSTRAINT "U_id_nauczyciela" UNIQUE ("Id_nauczyciela") INCLUDE ("Id_nauczyciela");
 B   ALTER TABLE ONLY public.klasa DROP CONSTRAINT "U_id_nauczyciela";
       public            postgres    false    204    204            �
           2606    16641    uczniowie U_id_ucznia 
   CONSTRAINT     W   ALTER TABLE ONLY public.uczniowie
    ADD CONSTRAINT "U_id_ucznia" UNIQUE (id_ucznia);
 A   ALTER TABLE ONLY public.uczniowie DROP CONSTRAINT "U_id_ucznia";
       public            postgres    false    212            �
           2606    16643    uczniowie Uczniowie_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY public.uczniowie
    ADD CONSTRAINT "Uczniowie_pkey" PRIMARY KEY (id_ucznia) INCLUDE (id_ucznia);
 D   ALTER TABLE ONLY public.uczniowie DROP CONSTRAINT "Uczniowie_pkey";
       public            postgres    false    212    212            �
           2606    16645    nauczyciele Uid_nauczyciela 
   CONSTRAINT     b   ALTER TABLE ONLY public.nauczyciele
    ADD CONSTRAINT "Uid_nauczyciela" UNIQUE (id_nauczyciela);
 G   ALTER TABLE ONLY public.nauczyciele DROP CONSTRAINT "Uid_nauczyciela";
       public            postgres    false    206            �
           2606    16647    relacje fk_id_ucznia_id_rodzica 
   CONSTRAINT     p   ALTER TABLE ONLY public.relacje
    ADD CONSTRAINT fk_id_ucznia_id_rodzica PRIMARY KEY (id_ucznia, id_rodzica);
 I   ALTER TABLE ONLY public.relacje DROP CONSTRAINT fk_id_ucznia_id_rodzica;
       public            postgres    false    210    210            �
           2606    16649    oceny oceny_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.oceny
    ADD CONSTRAINT oceny_pkey PRIMARY KEY (id_oceny);
 :   ALTER TABLE ONLY public.oceny DROP CONSTRAINT oceny_pkey;
       public            postgres    false    207            �
           2606    16651    godzina pk_id_godzina 
   CONSTRAINT     ]   ALTER TABLE ONLY public.godzina
    ADD CONSTRAINT pk_id_godzina PRIMARY KEY ("Id_godzina");
 ?   ALTER TABLE ONLY public.godzina DROP CONSTRAINT pk_id_godzina;
       public            postgres    false    203            �
           2606    16653    przedmioty pk_id_przedmiotu 
   CONSTRAINT     d   ALTER TABLE ONLY public.przedmioty
    ADD CONSTRAINT pk_id_przedmiotu PRIMARY KEY (id_przedmiotu);
 E   ALTER TABLE ONLY public.przedmioty DROP CONSTRAINT pk_id_przedmiotu;
       public            postgres    false    209            �
           2606    16655    rodzice pk_id_rodzica 
   CONSTRAINT     [   ALTER TABLE ONLY public.rodzice
    ADD CONSTRAINT pk_id_rodzica PRIMARY KEY (id_rodzica);
 ?   ALTER TABLE ONLY public.rodzice DROP CONSTRAINT pk_id_rodzica;
       public            postgres    false    211            �
           2606    16656    godzina Godzina_Id_dnia_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.godzina
    ADD CONSTRAINT "Godzina_Id_dnia_fkey" FOREIGN KEY ("Id_dnia") REFERENCES public.dzien("Id_dnia") NOT VALID;
 H   ALTER TABLE ONLY public.godzina DROP CONSTRAINT "Godzina_Id_dnia_fkey";
       public          postgres    false    202    203    2738            �
           2606    16661    lekcje fk_id_godziny    FK CONSTRAINT     �   ALTER TABLE ONLY public.lekcje
    ADD CONSTRAINT fk_id_godziny FOREIGN KEY ("Id_godziny") REFERENCES public.godzina("Id_godzina") NOT VALID;
 >   ALTER TABLE ONLY public.lekcje DROP CONSTRAINT fk_id_godziny;
       public          postgres    false    205    2740    203            �
           2606    16666    plan fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.plan
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY ("Id_klasy") REFERENCES public.klasa("Id_klasy") NOT VALID;
 :   ALTER TABLE ONLY public.plan DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    2742    208    204            �
           2606    16671    uczy fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczy
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY ("Id_klasy") REFERENCES public.klasa("Id_klasy") NOT VALID;
 :   ALTER TABLE ONLY public.uczy DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    213    2742    204            �
           2606    16676    uczniowie fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczniowie
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY (id_klasy) REFERENCES public.klasa("Id_klasy") NOT VALID;
 ?   ALTER TABLE ONLY public.uczniowie DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    212    2742    204            �
           2606    16681    plan fk_id_lekcji    FK CONSTRAINT     �   ALTER TABLE ONLY public.plan
    ADD CONSTRAINT fk_id_lekcji FOREIGN KEY ("Id_lekcji") REFERENCES public.lekcje("Id_lekcji") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 ;   ALTER TABLE ONLY public.plan DROP CONSTRAINT fk_id_lekcji;
       public          postgres    false    2746    208    205            �
           2606    16686    klasa fk_id_nauczyciela    FK CONSTRAINT     �   ALTER TABLE ONLY public.klasa
    ADD CONSTRAINT fk_id_nauczyciela FOREIGN KEY ("Id_nauczyciela") REFERENCES public.nauczyciele(id_nauczyciela) NOT VALID;
 A   ALTER TABLE ONLY public.klasa DROP CONSTRAINT fk_id_nauczyciela;
       public          postgres    false    204    206    2748            �
           2606    16691    lekcje fk_id_nauczyciela    FK CONSTRAINT     �   ALTER TABLE ONLY public.lekcje
    ADD CONSTRAINT fk_id_nauczyciela FOREIGN KEY ("Id_nauczyciela") REFERENCES public.nauczyciele(id_nauczyciela);
 B   ALTER TABLE ONLY public.lekcje DROP CONSTRAINT fk_id_nauczyciela;
       public          postgres    false    205    206    2748            �
           2606    16696    uczy fk_id_nauczyciela    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczy
    ADD CONSTRAINT fk_id_nauczyciela FOREIGN KEY ("Id_nauczyciela") REFERENCES public.nauczyciele(id_nauczyciela);
 @   ALTER TABLE ONLY public.uczy DROP CONSTRAINT fk_id_nauczyciela;
       public          postgres    false    206    213    2748            �
           2606    16701    lekcje fk_id_przedmiotu    FK CONSTRAINT     �   ALTER TABLE ONLY public.lekcje
    ADD CONSTRAINT fk_id_przedmiotu FOREIGN KEY ("Id_przedmiotu") REFERENCES public.przedmioty(id_przedmiotu) NOT VALID;
 A   ALTER TABLE ONLY public.lekcje DROP CONSTRAINT fk_id_przedmiotu;
       public          postgres    false    2756    209    205            �
           2606    16706    uczy fk_id_przedmiotu    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczy
    ADD CONSTRAINT fk_id_przedmiotu FOREIGN KEY ("Id_przedmiotu") REFERENCES public.przedmioty(id_przedmiotu) NOT VALID;
 ?   ALTER TABLE ONLY public.uczy DROP CONSTRAINT fk_id_przedmiotu;
       public          postgres    false    209    213    2756            �
           2606    16711    oceny fk_id_przedmiotu    FK CONSTRAINT     �   ALTER TABLE ONLY public.oceny
    ADD CONSTRAINT fk_id_przedmiotu FOREIGN KEY (id_przedmiotu) REFERENCES public.przedmioty(id_przedmiotu) NOT VALID;
 @   ALTER TABLE ONLY public.oceny DROP CONSTRAINT fk_id_przedmiotu;
       public          postgres    false    2756    209    207            �
           2606    16716    relacje fk_id_rodzica    FK CONSTRAINT     �   ALTER TABLE ONLY public.relacje
    ADD CONSTRAINT fk_id_rodzica FOREIGN KEY (id_rodzica) REFERENCES public.rodzice(id_rodzica) NOT VALID;
 ?   ALTER TABLE ONLY public.relacje DROP CONSTRAINT fk_id_rodzica;
       public          postgres    false    2760    210    211            �
           2606    16721    oceny fk_id_ucznia    FK CONSTRAINT     ~   ALTER TABLE ONLY public.oceny
    ADD CONSTRAINT fk_id_ucznia FOREIGN KEY (id_ucznia) REFERENCES public.uczniowie(id_ucznia);
 <   ALTER TABLE ONLY public.oceny DROP CONSTRAINT fk_id_ucznia;
       public          postgres    false    212    2762    207            �
           2606    16726    relacje fk_id_ucznia    FK CONSTRAINT     �   ALTER TABLE ONLY public.relacje
    ADD CONSTRAINT fk_id_ucznia FOREIGN KEY (id_ucznia) REFERENCES public.uczniowie(id_ucznia);
 >   ALTER TABLE ONLY public.relacje DROP CONSTRAINT fk_id_ucznia;
       public          postgres    false    212    2762    210            Z      x������ � �      [      x������ � �      \      x�3�4�4q�2�4�4q����� ��      ]      x������ � �      ^   }   x�M�1
�0��Y>L I�'=@��t������&����<�z�?)��{Vx��$P�a?�r薴�W?���`��x1-��<|�ʞrC���:VjE�����;�4�Δ�62e�м;c��n9�      _   O   x�3�4�420��54" �;��$�����DN.cN#�? P�������X�_�0Is���g�������� R�V      `      x������ � �      a   _   x�3��M,I�M,��N���2���,.�/�q�9��̬��V��)���2����3SAB@E��N��9�陉\f�n�@�D.sNϼ��"��\1z\\\ ^�"�      b      x������ � �      c      x������ � �      d     x�m�Mn�0���S�F�%ݕ]+D�ڮP7C0�k;��#Dv��]�(��$��Hlf$����ss87�/(!��r�`<�}QK%��G�Fi����ӹ0۠�E��E�\P1.$���wEQDY�L��7x+C��΢֖%&��=ww��~]e�
kz�GJ�ȴ��8��ۿ����^AEh����\Uq)	5���9����+��3M���V��hIW=�\D\�����C�*�E}3Ws�n���@�h)�"M(҄B�X܄�����I�r��\�g�c���4��      e      x������ � �     