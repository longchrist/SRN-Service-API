select * from srn_campaign;
select * from srn_voucher_campaign;
select * from srn_voucher_campaign_detail;
select * from srn_store;
select * from srn_campaign_detail;
insert into srn_voucher_campaign values (default,'Dummy Voucher Group 1',current_timestamp + interval '3 days',current_timestamp, current_timestamp);
insert into srn_voucher_campaign_detail values (default,10000000,'DUMMYVOUCHERCODE1',current_timestamp, current_timestamp);
insert into srn_voucher_campaign_detail values (default,10000000,'DUMMYVOUCHERCODE2',current_timestamp, current_timestamp);
insert into srn_voucher_campaign_detail values (default,10000000,'DUMMYVOUCHERCODE3',current_timestamp, current_timestamp);
insert into srn_voucher_campaign_detail values (default,10000000,'DUMMYVOUCHERCODE4',current_timestamp, current_timestamp);
insert into srn_voucher_campaign_detail values (default,10000000,'DUMMYVOUCHERCODE5',current_timestamp, current_timestamp);
insert into srn_campaign_detail values (default, 10000000, 'C01',10000000);
insert into srn_campaign_detail values (default, 10000000, 'C05',10000000);

explain analyze
select * from srn_campaign_detail scd join srn_campaign sc on scd.campaign_id = sc.id
left join srn_voucher_campaign svc on svc.voucher_campaign_id = scd.voucher_campaign_id
left join srn_voucher_campaign_detail svcd on svc.voucher_campaign_id = svcd.voucher_campaign_id
where scd.store_id = 'C05'


